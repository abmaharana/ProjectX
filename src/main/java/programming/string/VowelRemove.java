package programming.string;

public class VowelRemove {
    public static void main(String[] args) {
        String s = "prepinsta"; //output=prpnst
        String finalStr=" ";

        for (int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                continue;
            }else
                finalStr = finalStr + ch;
        }

        System.out.println(finalStr);
    }
}
