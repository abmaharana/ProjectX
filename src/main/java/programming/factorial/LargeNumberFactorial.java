package programming.factorial;

import java.math.BigInteger;

public class LargeNumberFactorial {
    public static void main(String[] args) {
        int n = 5;
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n ; i++) {
            result=result.multiply(BigInteger.valueOf(i));
        }
        System.out.println(result);
    }


}
