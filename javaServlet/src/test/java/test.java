
import java.io.IOException;
import java.util.Random;
import java.util.stream.LongStream;

public class test {
    public static void main(String []args) throws IOException {
        Random random = new Random();
        long x = random.nextLong();
        if(x<0) x = -x;
        System.out.println(x);
    }
}
