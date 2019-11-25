package Producer;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * @program: DistributedComuting2nd
 * @description: Produce the data from 1 tp 2014*512.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class Producer extends Thread
{
    private PipedOutputStream pipedOutputStream = new PipedOutputStream();
    private static int END_NUMBER = 1031168;//2014 * 512
    private static int TIMES_OF_ONE_NUMBER = 256;

    public PipedOutputStream getPipedOutputStream()
    {
        return  pipedOutputStream;
    }

    @Override
    public void run()
    {
        try
        {
            writeToPipe();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeToPipe() throws IOException
    {
        for(int i = 1; i <= 20; i++)
        {
            writeNumberToPipe(i);
        }
        pipedOutputStream.close();
    }

    public void writeNumberToPipe(int num) throws IOException
    {
        String s = String.valueOf(num);
        for(int i = 1; i <= TIMES_OF_ONE_NUMBER; i++)
        {
            pipedOutputStream.write(s.getBytes());
        }
    }
}