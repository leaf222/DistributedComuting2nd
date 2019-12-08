package HDWriter;
import MTWriter.RandomAccessFileMTWriter;
import Producer.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

/**
 * @program: DistributedComuting2nd
 * @description: Test HDWriters.
 * @author: Yifan Ye
 * @create: 2019/12/07
 **/
public class HDWriterTest
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        ConcurrentLinkedQueue<byte[]> queue = new ConcurrentLinkedQueue<byte[]>();

        int numOfThreads = 1;

        CountDownLatch countDownLatchForTime = new CountDownLatch(numOfThreads);
        CountDownLatch countDownLatchForStop = new CountDownLatch(1);

        Thread[] threads = new Thread[numOfThreads];

        Producer producer = new Producer(countDownLatchForStop, queue);
        long startTime = System.currentTimeMillis();//计时开始
        producer.start();

        for(int i = 0; i < numOfThreads; i++)
        {
            OutPutStreamHDWriter outPutStreamHDWriter = new OutPutStreamHDWriter(countDownLatchForTime,countDownLatchForStop,queue);
            threads[i] = outPutStreamHDWriter;
            threads[i].start();
        }


        countDownLatchForTime.await();//阻塞，所有线程结束才会结束

        long endTime=System.currentTimeMillis();//计时结束

        System.out.println(endTime-startTime);

    }
}