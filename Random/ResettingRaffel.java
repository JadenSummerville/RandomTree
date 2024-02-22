package Random;

import java.util.ArrayList;
import java.util.List;

public class ResettingRaffel<T>{
    private Raffel<T> main;
    private Raffel<T> backup;
    private int numOfResets;
    public ResettingRaffel(Raffel<T> raffel){
        this.main = raffel;
        this.backup = new Raffel<>();
        numOfResets = 0;
    }
    /**
     * Return a random 'T' with each index weighted by it's size
     * 
     * @return random 'T' with each index weighted by it's size or null iff this is empty
    */
    public T peak(){
        return this.peak();
    }
    /**
     * Remove and return a random 'T' with each index weighted by it's size.
     * If we become empty, reset.
     * 
     * @modefies 'this' removes value that was returned from 'this'. Iff 'this' is empty, this is not modifies
     * @return random 'T' with each index weighted by it's size or null iff this is empty
    */
    public T pull(){
        T value = main.pull();
        backup.add(value);
        if(main.isEmpty()){
            Raffel<T> o = main;
            main = backup;
            backup = o;
            numOfResets++;
        }
        return value;
    }
    /**
     * Return number of times 'this' has reset
     * 
     * @return number of times 'this' has reset
    */
    public int getResetAmount(){
        return this.numOfResets;
    }
    /**
     * Reset the reset counter
    */
    public void resetResetCounter(){
        this.numOfResets = 0;
    }
    /**
     * Remove and return 'numOfItems' random 'T's with each index weighted by it's size.
     * If we become empty, reset.
     * 
     * @param numOfItems number of 'T' to return
     * @modefies 'this' removes values that were returned from 'this'
     * @throws Exception Iff numOfItems < 0.
     * @return random List of all 'T' with each index weighted by it's size
    */
    public List<T> pull(int numOfItems){
        if(numOfItems < 0){
            throw new Error();
        }
        List<T> goal = new ArrayList<>();
        for(int i = 0; i != numOfItems; i++){
            goal.add(pull());
        }
        return goal;
    }
}