class Singleton{
   private Singleton obj=new Singleton();

    //Private constructor so no one can create object from outside
    private Singleton();

    public static Singleton getInstance(){
        return obj;
    }


   public void showMessage(){
     System.out.println("Hello this is for accesing data");
   }
}


public class Main{
    public static void main(String []args){
      Singleton s=Singleton.getInstance();
    }
}

//Lazy and better way
class Singleton{
   private Singleton obj;

    public static Singleton getInstance(){
        if(obj==null){ obj = new Singleton; }
        return obj;
    }
}


//Most optimiseid way specially whn used in multitreated
public class Singleton {

    // 1. Private static variable to hold the single instance
    private static volatile Singleton instance;

    // 2. Private constructor so no one can create object from outside
    private Singleton() {
        System.out.println("Singleton instance created");
    }

    // 3. Public static method to get the instance
    public static Singleton getInstance() {
        if (instance == null) { // First check (no locking yet)
            synchronized (Singleton.class) { // Lock only when needed
                if (instance == null) { // Second check after acquiring lock
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}

/*
* as synchronized function is expensieve thats why we use 2 locks first check then synchronized then again check
* volatile we use to prevent CPU reordering issue and cache of core's sync up issue
*/