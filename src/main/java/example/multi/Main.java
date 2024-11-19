package example.multi;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        example2();
    }

    private static void example1() throws InterruptedException {
        Box commonBox = new Box();
        List<Thread> threads = List.of(
                                new Thread(()->workWithBox(commonBox, "a", 7, 1500)),
                                new Thread(()->workWithBox(commonBox, "B", 5, 2000)),
                                new Thread(()->workWithBox(commonBox, "C", 25, 500))
        ) ;
        threads.forEach(Thread::start);
        Thread.sleep(13000);
        System.out.println("commonBox = " + commonBox);
    }

    public static void workWithBox(Box box, String msg, int n, int x){
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(x);
                box.append(msg);
                System.out.println(Thread.currentThread().getName() + " " +(int)((i /(double)n) * 100) + " %");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void example2() throws InterruptedException {
        ListBox commonBox = new ListBox();
        List<Thread> threads = List.of(
                new Thread(()->workWithListBox(commonBox, "a", 7, 1500)),
                new Thread(()->workWithListBox(commonBox, "B", 5, 2000)),
                new Thread(()->workWithListBox(commonBox, "C", 25, 500))
        ) ;
        threads.forEach(Thread::start);
        Thread.sleep(13000);
        System.out.println("commonBox = " + commonBox);
    }

    public static void workWithListBox(ListBox box, String msg, int n, int x){
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(x);
                box.append(msg);
                System.out.println(Thread.currentThread().getName() + " " +(int)((i /(double)n) * 100) + " %");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Box{
    String value="";

    public String getValue() {
        return value;
    }

    public void append(String s){
        value+=" "+s;
    }

    @Override
    public String toString() {
        return "Box{" +    value +     '}';
    }
}

class ListBox{
    List<String> value=new ArrayList<>();

    public void append(String s){
        value.add(s);
    }

    @Override
    public String toString() {
        return "Box{" +    value +     '}';
    }
}