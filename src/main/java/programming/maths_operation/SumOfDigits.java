package programming.maths_operation;

public class SumOfDigits {
    public static void main(String[] args) {
        int n = 12349;
        int digit;
        int sum=0;
        while(n!=0){
            digit = n % 10;
            sum = sum + digit;
            n = n / 10;
        }
        System.out.println(sum);
    }
}
