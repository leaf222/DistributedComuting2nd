import MTWriter.*;
import Producer.*;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @program: DistributedComuting2nd
 * @description: Test.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class test
{

    public static void main(String[] args) throws IOException, InterruptedException
    {
        long startTime = System.currentTimeMillis();

        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

        int numOfThreads = 1;

        CountDownLatch countDownLatchForTime = new CountDownLatch(numOfThreads);
        CountDownLatch countDownLatchForStop = new CountDownLatch(1);
        Thread[] threads = new Thread[numOfThreads];

        Producer producer = new Producer(countDownLatchForStop, queue);



        for(int i = 0; i < numOfThreads; i++)
        {
            OutputStreamMTWriter outputStreamMTWriter = new OutputStreamMTWriter(countDownLatchForTime,countDownLatchForStop, queue);
            threads[i] = outputStreamMTWriter;
            threads[i].start();
        }

        producer.start();
        countDownLatchForTime.await();

        long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }
}