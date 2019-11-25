package MTWriter;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    @Override
    public void createFile() throws FileNotFoundException
    {
        super.createFile();
        outputStream = new BufferedOutputStream(new FileOutputStream(FILE_PATH));
    }

    @Override
    public void run()
    {
        try
        {
            PrintThePipe();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void PrintThePipe() throws IOException
    {
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = super.getPipedInputStream().read(buf)) != -1)
        {
            try
            {
                String s = new String(buf,0,len);
                outputStream.write(buf, 0, len);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}