package MTWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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

    public OutputStreamMTWriter() throws FileNotFoundException
    {
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