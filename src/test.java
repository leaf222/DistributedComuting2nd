import MTWriter.*;
import Producer.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @program: DistributedComuting2nd
 * @description: Test.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class test extends Thread
{
    @Override
    public void run()
    {
        System.out.println("111");
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Producer producer = new Producer();
        MTWriter mtWriter = new BufferedMTWriter();

        PipedOutputStream outputStream = producer.getPipedOutputStream();
        PipedInputStream inputStream = mtWriter.getPipedInputStream();

        try
        {
            outputStream.connect(inputStream);

            producer.start();
            mtWriter.start();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}