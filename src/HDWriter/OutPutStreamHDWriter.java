package HDWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @program: DistributedComuting2nd
 * @description: Use OutputStream to write into hdfs.
 * @author: Yifan Ye
 * @create: 2019/12/08
 **/
public class OutPutStreamHDWriter extends HDWriter
{
    private static String destination = "hdfs://localhost:9000/data/OutputStreamTest";//写入hdfs文件系统的位置
    private OutputStream outputStream = null;

    public OutPutStreamHDWriter(CountDownLatch c1, CountDownLatch c2, ConcurrentLinkedQueue<byte[]> q) throws IOException
    {
        super(c1, c2, q);
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(destination),conf);
        outputStream = fs.create(new Path(destination));
    }

    @Override
    public void run()
    {
        try
        {
            PrintTheQueue(outputStream);
            outputStream.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}