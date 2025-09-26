package programming;

public class MissingNumbersInArray {
    public static void main(String[] args) {
        // missing number in the array
        int[] n = {1,2,4,5,6};
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            sum = sum + n[i];
        }
        int total = (n.length+1)*(n.length+2)/2;
        System.out.println(total - sum);


    }
}
