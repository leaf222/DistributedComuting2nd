import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @program: DistributedComuting2nd
 * @description: Write the data to local file system.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class MTWriter extends Thread
{
    private PipedInputStream pipedInputStream = new PipedInputStream();
    private static String FILE_PATH = "E:/Code/IntelJ/DistributedComuting2nd/log.txt";

    public PipedInputStream getPipedInputStream()
    {
        return  pipedInputStream;
    }

    @Override
    public void run()
    {
        PrintThePipe();
    }

    public void PrintThePipe()
    {
        int total=0;
        while (true)
        {
            byte[] buf = new byte[1024];
            try
            {
                int len = pipedInputStream.read(buf);
                total += len;
                if(len == -1) break;
                String s = new String(buf,0,len);
                System.out.println(s);

            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        Producer producer = new Producer();
        MTWriter mtWriter = new MTWriter();
        PipedInputStream in = mtWriter.getPipedInputStream();
        PipedOutputStream out = producer.getPipedOutputStream();

        try
        {
            in.connect(out);

            producer.start();
            mtWriter.start();

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}