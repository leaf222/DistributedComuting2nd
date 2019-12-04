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
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

        int numOfThreads = 16;

        CountDownLatch countDownLatchForTime = new CountDownLatch(numOfThreads);
        CountDownLatch countDownLatchForStop = new CountDownLatch(1);

        Thread[] threads = new Thread[numOfThreads];

        Producer producer = new Producer(countDownLatchForStop, queue);

        long startTime = System.currentTimeMillis();//计时开始
        producer.start();

        for(int i = 0; i < numOfThreads; i++)
        {
            RandomAccessFileMTWriter rafMTWriter = new RandomAccessFileMTWriter(countDownLatchForTime,countDownLatchForStop,queue);
            threads[i] = rafMTWriter;
            threads[i].start();
        }


        countDownLatchForTime.await();//阻塞，所有线程结束才会结束

        long endTime=System.currentTimeMillis();//计时结束

        System.out.println(endTime-startTime);
    }
}