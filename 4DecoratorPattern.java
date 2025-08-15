
interface Pizza{
   int getCost();
}

class Pizza1 implements Pizza {
  @Override
  public int getCost(){return 10;}
}

interface PizzaDecorator extends Pizza{}

class CheeseDecorator implements PizzaDecorator{
  public Pizza p;
  public CheeseDecorator(Pizza P){
    this.p=P;
  }
  public int getCost(){
    return p.getCost()+5;
  }
}

class MayoDecorator implements PizzaDecorator{
  public Pizza p;
  public MayoDecorator(Pizza P){
    this.p=P;
  }
  public int getCost(){
    return p.getCost()+15;
  }
}

public class Main {
  public static void main(String[] args) {
    Pizza p=new CheeseDecorator(new MayoDecorator(new Pizza1()));
    System.out.println(p.getCost());
  }
}