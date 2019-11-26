package Producer;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @program: DistributedComuting2nd
 * @description: Produce the data from 1 tp 2014*512.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class Producer extends Thread
{

    private CountDownLatch countDownLatch = null;
    private ConcurrentLinkedQueue<String> queue = null;

    private static int END_NUMBER = 1031168;//2014 * 512
    private static int TIMES_OF_ONE_NUMBER = 256;

    public Producer(CountDownLatch c, ConcurrentLinkedQueue<String> q)
    {
        this.countDownLatch = c;
        this.queue = q;
    }

    @Override
    public void run()
    {
        try
        {
            writeToQueue();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeToQueue() throws IOException
    {
        for(int i = 1; i <= 1000; i++)
        {
            writeNumberToQueue(i);
        }
        System.out.println("数据生成已完成");
        countDownLatch.countDown();
    }

    public void writeNumberToQueue(int num) throws IOException
    {
        String s = String.valueOf(num);
        for(int i = 1; i <= TIMES_OF_ONE_NUMBER; i++)
        {
            queue.add(String.valueOf(num));
        }
    }
}