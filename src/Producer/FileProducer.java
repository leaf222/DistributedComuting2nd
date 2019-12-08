package Producer;

import java.io.*;

/**
 * @program: DistributedComuting2nd
 * @description: Write the data into a file.
 * @author: Yifan Ye
 * @create: 2019/12/08
 **/
public class FileProducer
{
    private static String FILE_PATH = "src.txt";
    private static int NUM = 2014 * 512;

    //int转byte[]
    public static byte[] intToByteArray(int a)
    {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    //检查文件是否存在，如不存在，则创建
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

    //写入
    public void Write() throws IOException
    {
        OutputStream outputStream = new FileOutputStream(FILE_PATH, true);
        for(int i = 1; i <= NUM; i++)
        {
            for(int j = 1; j <= 256; j++)
            {
                outputStream.write(intToByteArray(i));
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        FileProducer f = new FileProducer();
        f.Write();
    }
}