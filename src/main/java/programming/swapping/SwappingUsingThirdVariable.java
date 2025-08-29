package programming.swapping;

public class SwappingUsingThirdVariable {
    public static void main(String[] args) {
        int a = 10, b = 20;
        int temp;

        System.out.println("a= "+a);
        System.out.println("b= "+b);

        temp=b;
        b=a;
        a=temp;

        System.out.println("a="+a);
        System.out.println("b="+b);
    }
}
