package MTWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @program: DistributedComuting2nd
 * @description: Use RandomAccessFile to write data.
 * @author: Yifan Ye
 * @create: 2019/12/04
 **/
public class RandomAccessFileMTWriter extends MTWriter
{
    private RandomAccessFile randomAccessFile = null;
    private static String FILE_PATH = "E:/Code/IntelJ/DistributedComuting2nd/RandomAccessFileIOLog";

    public RandomAccessFileMTWriter(CountDownLatch c1, CountDownLatch c2, ConcurrentLinkedQueue<String> q) throws FileNotFoundException
    {
        super(c1, c2, q);
        super.createFile(FILE_PATH);
        randomAccessFile = new RandomAccessFile(FILE_PATH,"rw");
    }

    @Override
    public void run()
    {
        PrintTheQueue();
    }

    public void PrintTheQueue()
    {
        synchronized (queue)
        {
            while (true)
            {
                if (!queue.isEmpty())
                {
                    try
                    {
                        randomAccessFile.seek(randomAccessFile.length());
                        randomAccessFile.write(queue.poll().getBytes("UTF-8"));
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if(countDownLatchForStop.getCount() == 0 && queue.isEmpty())
                {
                    countDownLatchForTime.countDown();
                    break;
                }
            }
        }
    }
}