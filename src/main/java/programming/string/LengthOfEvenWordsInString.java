package programming.string;

public class LengthOfEvenWordsInString {
    public static void main(String[] args) {
        String s="        Sky is blue     and vast";
        String strArray[] = s.trim().split("\\s+");

        for (String word : strArray) {
            if (word.length() % 2 == 0){
                System.out.println(word+"==>"+word.length());
            }
        }
    }
}
