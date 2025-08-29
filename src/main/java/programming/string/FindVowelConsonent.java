package programming.string;

public class FindVowelConsonent {
    public static void main(String[] args) {
        String s = "abhishek";
        char ch[] = s.toCharArray();
        int vowel=0;
        for(char c : ch) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowel = vowel + 1;
            }
        }
        System.out.println("Vowel:"+vowel);
        System.out.println("Consonents:"+(s.length()-vowel));
    }
}
