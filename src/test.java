/**
 * @program: DistributedComuting2nd
 * @description: Test.
 * @author: Yifan Ye
 * @create: 2019/11/25
 **/
public class test extends Thread
{
    @Override
    public void run()
    {
        System.out.println("111");
    }

    public static void main(String[] args)
    {

        test test = new test();
        test.start();
    }
}