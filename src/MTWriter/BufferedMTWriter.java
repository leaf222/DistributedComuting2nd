package MTWriter;

import java.io.*;
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

    private static String FILE_PATH = "E:/Code/IntelJ/DistributedComuting2nd/BufferedIOLog.txt";

    public BufferedMTWriter(PipedInputStream pipedInputStream) throws FileNotFoundException
    {
        super(pipedInputStream);
        super.createFile(FILE_PATH);
        outputStream = new BufferedOutputStream(new FileOutputStream(FILE_PATH));
    }

    @Override
    public void run()
    {
        try
        {
            PrintThePipe(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}