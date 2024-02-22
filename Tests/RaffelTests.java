package Tests;

import java.util.HashMap;

import Random.Raffel;

public class RaffelTests {
    public static void main(String[] ars){
        Raffel<String> raffel = new Raffel<String>();
        
        /*
        Raffel<String> raffel = new Raffel<String>();
        raffel.add("1");
        System.out.println(raffel.size());
        System.out.println(raffel.typeSize());
        System.out.println();
        raffel.add("2", 3);
        System.out.println(raffel.size());
        System.out.println(raffel.typeSize());
        System.out.println();
        raffel.add("3", 2);
        System.out.println(raffel.size());
        System.out.println(raffel.typeSize());
        System.out.println();
        raffel.add("3", 2);
        System.out.println(raffel.size());
        System.out.println(raffel.typeSize());
        System.out.println();
        raffel.add("4", 2);
        System.out.println(raffel.size());
        System.out.println(raffel.typeSize());
        System.out.println();
        raffel.add("5", 2);
        System.out.println(raffel.size());
        System.out.println(raffel.typeSize());
        System.out.println();
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i = 0; i != 6; i++){
            String pull = raffel.pull();
            if(!hashMap.containsKey(pull)){
                hashMap.put(pull, 1);
            }else{
                Integer amount = hashMap.get(pull);
                hashMap.put(pull, amount + 1);
            }
        }
        for(String value: hashMap.keySet()){
            System.out.println(value + " " + hashMap.get(value));
        }
        */
    }
}
