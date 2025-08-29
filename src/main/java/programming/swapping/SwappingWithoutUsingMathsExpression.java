package programming.swapping;

public class SwappingWithoutUsingMathsExpression {
    public static void main(String[] args) {
        //Bit-wise operator=EX-OR (^)
        int a=10,b=20;
        System.out.println("a= "+a);
        System.out.println("b= "+b);

        a=a^b;
        b=a^b;
        a=a^b;

        System.out.println("=====After swapping====");
        System.out.println("a= "+a);
        System.out.println("b= "+b);

    }
}
