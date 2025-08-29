package programming.reverse;

public class NumberReversal {
    public static void main(String[] args) {
        long number = 2187389789L;
        long lastDigit;
        long reverse=0;

        while(number!=0){
            lastDigit=number%10;
            reverse=reverse*10+lastDigit;
            number=number/10;
        }
        System.out.println(reverse);
    }
}
