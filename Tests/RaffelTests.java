package Tests;

import java.util.HashMap;

import Random.Raffel;

public class RaffelTests {
    public static void main(String[] ars){
        Raffel<String> raffel = new Raffel<String>();
        raffel.add("1");
        raffel.add("2");
        raffel.add("3");
        raffel.add("4");
        System.out.println("Start:");
        displaySizes(raffel);
        raffel.resize(946);
        displaySizes(raffel);
        //displayPull(raffel, 500);
        //displaySizes(raffel);
        raffel.add("4", 300);
        displaySizes(raffel);
        raffel.resize(946);
        displaySizes(raffel);
    }
    public static void displayPull(Raffel<String> raffel, int amountToPull){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i = 0; i != amountToPull; i++){
            String pull = raffel.pull();
            if(!hashMap.containsKey(pull)){
                hashMap.put(pull, 1);
            }else{
                Integer amount = hashMap.get(pull);
                hashMap.put(pull, amount + 1);
            }
        }
        System.out.println("Displaying " + amountToPull + " peaked amounts:");
        for(String value: hashMap.keySet()){
            System.out.println(value + " " + hashMap.get(value));
        }
        System.out.println();
    }
    public static void displayPeak(Raffel<String> raffel, int amountToPull){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i = 0; i != amountToPull; i++){
            String peak = raffel.peak();
            if(!hashMap.containsKey(peak)){
                hashMap.put(peak, 1);
            }else{
                Integer amount = hashMap.get(peak);
                hashMap.put(peak, amount + 1);
            }
        }
        System.out.println("Displaying " + amountToPull + " pulled amounts:");
        for(String value: hashMap.keySet()){
            System.out.println(value + " " + hashMap.get(value));
        }
        System.out.println();
    }
    public static void displaySizes(Raffel<String> raffel){
        System.out.println("Displaying raffel sizes:");
        for(String ouput: raffel.getPossibleValues()){
            System.out.println(ouput + " " + raffel.getAmount(ouput));
        }
        System.out.println();
    }
}
