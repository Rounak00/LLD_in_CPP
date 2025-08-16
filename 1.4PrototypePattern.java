
interface Prototype{
    Prototype clone();
}

class Student implements Prototype{
    private int age;
    public String  name;
    private int roll;
    public Student(int age, int roll,String name){
        this.age=age;
        this.name=name;
        this.roll=roll;
    }

    @Overide
    public Prototype clone(){
       return new Student(age,roll,name);
    }
}

public class Main{
    public static void main(String args[]){
        Student n=new Student(10,2,"Rounak");
        Student cloneN=n.clone();
    }
}