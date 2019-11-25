import MTWriter.*;
import Producer.*;

import java.io.*;

/**
 * @program: DistributedComuting2nd
 * @description: Test.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class test
{

    public static void main(String[] args) throws IOException
    {

        int numOfThreads = 2;


        Producer producer = new Producer();

        PipedOutputStream outputStream = producer.getPipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream();
        outputStream.connect(inputStream);

        producer.start();

        for(int i = 0; i < numOfThreads; i++)
        {
            PrintWriterMTWriter mtWriter = new PrintWriterMTWriter(inputStream);

            mtWriter.start();
        }

    }
}