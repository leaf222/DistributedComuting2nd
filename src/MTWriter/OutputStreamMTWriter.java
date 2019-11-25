package MTWriter;

import java.io.*;

/**
 * @program: DistributedComuting2nd
 * @description: Use OutputStream to write the files.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class OutputStreamMTWriter extends MTWriter
{
    private OutputStream outputStream = null;
    private static String FILE_PATH = "E:/Code/IntelJ/DistributedComuting2nd/OutputStreamIOLog.txt";

    public OutputStreamMTWriter(PipedInputStream pipedInputStream) throws FileNotFoundException
    {
        super(pipedInputStream);
        super.createFile(FILE_PATH);
        outputStream = new FileOutputStream(FILE_PATH);
    }

    @Override
    public void run()
    {
        try
        {
            PrintThePipe(outputStream);
            outputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}