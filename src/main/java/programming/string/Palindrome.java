package programming.string;

public class Palindrome {
    public static void main(String[] args) {
        String s = "madam";
        String rev="";
        for (int i = s.length()-1; i >= 0 ; i--) {
            rev=rev+s.charAt(i);
        }

        if (rev.equals(s))
            System.out.println("Palindrome");
        else
            System.out.println("Not Palindrome");
    }
}
