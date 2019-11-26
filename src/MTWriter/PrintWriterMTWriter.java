package MTWriter;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @program: DistributedComuting2nd
 * @description: Use PrintWriter to write the data.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class PrintWriterMTWriter extends MTWriter
{
    private PrintWriter printWriter = null;
    private  static String FILE_PATH = "E:/Code/PrintWriterIOLog.txt";

    public PrintWriterMTWriter(CountDownLatch c1, CountDownLatch c2, ConcurrentLinkedQueue<String> q) throws IOException
    {
        super(c1,c2,q);
        super.createFile(FILE_PATH);
        printWriter = new PrintWriter(new FileWriter(FILE_PATH,true));
    }

    @Override
    public void run()
    {
        try
        {
            PrintTheQueue();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        printWriter.flush();
        printWriter.close();
    }

    public void PrintTheQueue() throws IOException
    {
        synchronized (queue)
        {
            while (true)
            {
                if (!queue.isEmpty())
                {
                    try
                    {
                        printWriter.write(String.valueOf(queue.poll().getBytes("UTF-8")));
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