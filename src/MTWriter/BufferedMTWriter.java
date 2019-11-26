package MTWriter;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.zip.CheckedInputStream;

/**
 * @program: DistributedComuting2nd
 * @description: User BufferedOutPutStream to write.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class BufferedMTWriter extends MTWriter
{
    private BufferedOutputStream outputStream = null;

    private static String FILE_PATH = "E:/Code/BufferedIOLog.txt";

    public BufferedMTWriter(CountDownLatch c1, CountDownLatch c2, ConcurrentLinkedQueue<String> q) throws FileNotFoundException
    {
        super(c1,c2, q);
        super.createFile(FILE_PATH);
        outputStream = new BufferedOutputStream(new FileOutputStream(FILE_PATH,true));
    }

    @Override
    public void run()
    {
        try
        {
            PrintTheQueue(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}