package hello.core.singleton;

public class StatefulService {
//    private int price;
    public int order(String name, int price){
        System.out.println("name + price = " + name + price);
        return price;
    }

//    public int getPrice(){
////        return price;
//    }
}
