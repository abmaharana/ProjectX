package programming.singleton;

public class Singleton {
    private static Singleton s;
    private Singleton(){

    }

    public static Singleton x(){
        if(s==null){
            s = new Singleton();
        }
        return s;
    }
}

