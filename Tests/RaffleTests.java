package Tests;

import java.util.HashMap;

import Random.Raffle;

public class RaffleTests {
    public static void main(String[] ars){
        Raffle<String> raffle = new Raffle<String>();
        raffle.add("1");
        raffle.add("2");
        raffle.add("3");
        raffle.add("4");
        System.out.println("Start:");
        displaySizes(raffle);
        raffle.resize(946);
        displaySizes(raffle);
        //displayPull(raffle, 500);
        //displaySizes(raffle);
        raffle.add("4", 300);
        displaySizes(raffle);
        raffle.resize(946);
        displaySizes(raffle);
    }
    public static void displayPull(Raffle<String> raffle, int amountToPull){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i = 0; i != amountToPull; i++){
            String pull = raffle.pull();
            if(!hashMap.containsKey(pull)){
                hashMap.put(pull, 1);
            }else{
                Integer amount = hashMap.get(pull);
                hashMap.put(pull, amount + 1);
            }
        }
        System.out.println("Displaying " + amountToPull + " peeked amounts:");
        for(String value: hashMap.keySet()){
            System.out.println(value + " " + hashMap.get(value));
        }
        System.out.println();
    }
    public static void displayPeek(Raffle<String> raffle, int amountToPull){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i = 0; i != amountToPull; i++){
            String peek = raffle.peek();
            if(!hashMap.containsKey(peek)){
                hashMap.put(peek, 1);
            }else{
                Integer amount = hashMap.get(peek);
                hashMap.put(peek, amount + 1);
            }
        }
        System.out.println("Displaying " + amountToPull + " pulled amounts:");
        for(String value: hashMap.keySet()){
            System.out.println(value + " " + hashMap.get(value));
        }
        System.out.println();
    }
    public static void displaySizes(Raffle<String> raffle){
        System.out.println("Displaying raffle sizes:");
        for(String ouput: raffle.getPossibleValues()){
            System.out.println(ouput + " " + raffle.getAmount(ouput));
        }
        System.out.println();
    }
}
