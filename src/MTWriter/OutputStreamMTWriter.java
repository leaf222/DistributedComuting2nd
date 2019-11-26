package MTWriter;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @program: DistributedComuting2nd
 * @description: Use OutputStream to write the files.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class OutputStreamMTWriter extends MTWriter
{
    private OutputStream outputStream = null;
    private static String FILE_PATH = "E:/Code/OutputStreamIOLog.txt";

    public OutputStreamMTWriter(CountDownLatch c1, CountDownLatch c2, ConcurrentLinkedQueue<String> q) throws FileNotFoundException
    {
        super(c1,c2,q);
        super.createFile(FILE_PATH);
        outputStream = new FileOutputStream(FILE_PATH,true);
    }

    @Override
    public void run()
    {
        try
        {
            PrintTheQueue(outputStream);
            outputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}