package MTWriter;

import Producer.Producer;

import java.io.*;

/**
 * @program: DistributedComuting2nd
 * @description: Write the data to local file system.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class MTWriter extends Thread
{
    private PipedInputStream pipedInputStream = new PipedInputStream();
    protected static String FILE_PATH = "E:/Code/IntelJ/DistributedComuting2nd/log.txt";

    public PipedInputStream getPipedInputStream()
    {
        return  pipedInputStream;
    }

    public void createFile() throws FileNotFoundException
    {
        File file = new File(FILE_PATH);
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void PrintThePipe() throws IOException{}

}