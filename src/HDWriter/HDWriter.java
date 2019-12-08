package HDWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @program: DistributedComuting2nd
 * @description: The Parent class for all HDWriters.
 * @author: Yifan Ye
 * @create: 2019/12/04
 **/
public class HDWriter extends Thread
{
    private ConcurrentLinkedQueue<byte[]> queue = null;
    private static String destination = "hdfs://localhost:9000/data/test";//写入hdfs文件系统的位置
    protected CountDownLatch countDownLatchForTime = null;
    protected CountDownLatch countDownLatchForStop = null;


    public HDWriter(CountDownLatch c1, CountDownLatch c2, ConcurrentLinkedQueue<byte[]> q)
    {
        this.countDownLatchForTime = c1;
        this.countDownLatchForStop = c2;
        this.queue = q;
    }


    public void PrintTheQueue(OutputStream outputStream)
    {
        synchronized (queue)
        {
            while (true)
            {
                if (!queue.isEmpty())
                {
                    try
                    {
                        outputStream.write(queue.poll());
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