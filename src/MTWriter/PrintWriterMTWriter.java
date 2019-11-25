package MTWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: DistributedComuting2nd
 * @description: Use PrintWriter to write the data.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class PrintWriterMTWriter extends MTWriter
{
    private PrintWriter printWriter = null;
    private  static String FILE_PATH = "E:/Code/IntelJ/DistributedComuting2nd/PrintWriterIOLog.txt";

    public PrintWriterMTWriter() throws FileNotFoundException
    {
        super.createFile(FILE_PATH);
        printWriter = new PrintWriter(FILE_PATH);
    }

    @Override
    public void run()
    {
        try
        {
            PrintThePipe();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        printWriter.flush();
        printWriter.close();
    }

    public void PrintThePipe() throws IOException {
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = super.getPipedInputStream().read(buf)) != -1)
        {
            String s = new String(buf,0,len);
            printWriter.write(new String(buf, 0 , len));
        }
    }
}