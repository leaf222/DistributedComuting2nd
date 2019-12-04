package MTWriter;

import Producer.Producer;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @program: DistributedComuting2nd
 * @description: Write the data to local file system.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public abstract class MTWriter extends Thread
{
    protected CountDownLatch countDownLatchForTime = null;

    protected CountDownLatch countDownLatchForStop = null;

    protected ConcurrentLinkedQueue<String> queue = null;

    public MTWriter(CountDownLatch c1, CountDownLatch c2, ConcurrentLinkedQueue<String> q)
    {
        this.countDownLatchForTime = c1;
        this.countDownLatchForStop = c2;
        this.queue = q;
    }

    //检查文件是否存在，如不存在，则创建
    public void createFile(String path) throws FileNotFoundException
    {
        File file = new File(path);
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void PrintTheQueue(OutputStream outputStream) throws IOException
    {
        synchronized (queue)
        {
            while (true)
            {
                if (!queue.isEmpty())
                {
                    try
                    {
                        outputStream.write(queue.poll().getBytes("UTF-8"));
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    countDownLatchForTime.countDown();
                    break;
                }
            }
        }
    }
}