package MTWriter;

import Producer.Producer;

import java.io.*;

/**
 * @program: DistributedComuting2nd
 * @description: Write the data to local file system.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public abstract class MTWriter extends Thread
{
    private PipedInputStream pipedInputStream = new PipedInputStream();

    public PipedInputStream getPipedInputStream()
    {
        return  pipedInputStream;
    }

    //检查文件是否存在，如不存在，则创建
    public void createFile(String path) throws FileNotFoundException
    {
        File file = new File(path);
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

    public abstract void PrintThePipe() throws IOException;

}