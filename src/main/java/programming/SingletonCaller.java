public class SingletonCaller {
    public static void main(String[] args) {
        Singleton d1 = singleton.x();
        System.out.println(d1);

        Singleton d2 = Singleton.x();
        System.out.println(d2);
    }
}

