package programming.string;

public class PrintTheLengthOfLastWord {
    public static void main(String[] args) {
        String s = "       Hello      World        ";
        char charArray[] = s.toCharArray();
        int count=0;
        for (int i = charArray.length-1; i >= 0 ; i--) {
            if (charArray[i] != ' '){
                count = count+1;
            }else if(count>0){
                System.out.println(count);
                break;
            }
        }

    }
}
