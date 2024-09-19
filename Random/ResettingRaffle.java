package Random;

import java.util.ArrayList;
import java.util.List;

/**
 * A ResettingRaffle allows you to insert objets and then randomly call them.
 * Afer calling the object, it is removed. The raffle will reset when emptied
 * @param <T> objets to be randomly generated.
*/
public class ResettingRaffle<T> {
    private Raffle<T> main;
    private Raffle<T> backup;
    private int numOfResets;
    /**
     * Creates a ResettingRaffle from 'raffle'.
     * 
     * @param raffle raffle to pull from
     * @throws Error iff raffle.typeSize() < 2
    */
    public ResettingRaffle(Raffle<T> raffle) {
        if(raffle.typeSize() < 2) {
            throw new Error();
        }
        this.main = raffle;
        this.backup = new Raffle<>();
        numOfResets = 0;
    }
    /**
     * Return a random 'T' with each index weighted by it's size
     * 
     * @requires return value is not edited
     * @return random 'T' with each index weighted by it's size or null iff this is empty
    */
    public T peek() {
        return main.peek();
    }
    /**
     * Remove and return a random 'T' with each index weighted by it's size.
     * If we become empty, reset.
     * 
     * @modefies 'this' removes value that was returned from 'this'. Iff 'this' is empty, this is not modifies
     * @requires return value is not edited
     * @return random 'T' with each index weighted by it's size or null iff this is empty
    */
    public T pull() {
        T value = main.pull();
        backup.add(value);
        if(main.isEmpty()) {
            Raffle<T> o = main;
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
    public int getResetAmount() {
        return this.numOfResets;
    }
    /**
     * Reset the reset counter
    */
    public void resetResetCounter() {
        this.numOfResets = 0;
    }
    /**
     * Remove and return 'numOfItems' random 'T's with each index weighted by it's size.
     * If we become empty, reset.
     * 
     * @param numOfItems number of 'T' to return
     * @modefies 'this' removes values that were returned from 'this'
     * @throws Exception Iff numOfItems < 0
     * @requires return values are not edited
     * @return random List of all 'T' with each index weighted by it's size
    */
    public List<T> pull(int numOfItems) {
        if(numOfItems < 0) {
            throw new Error();
        }
        List<T> goal = new ArrayList<>();
        for(int i = 0; i != numOfItems; i++) {
            goal.add(pull());
        }
        return goal;
    }
    /**
     * return number of items in 'this' before reset
     * 
     * @return number of items in 'this'
    */
    public int size() {
        return this.main.size();
    }
    /**
     * return number of items in 'this' total even if we have already seen a value
     * 
     * @return number of items in 'this' total
    */
    public int sizeTotal() {
        return this.size() + this.backup.size();
    }
    /**
     * return number of item types stored in 'this' that ca still be seen before a reset
     * 
     * @return number of item types stored in 'this'
    */
    public int typeSize() {
        return this.main.typeSize();
    }
    /**
     * return number of item types stored in 'this' total even if a type requires a reset to be seen
     * 
     * @return number of item types stored in 'this' total
    */
    public int typeSizeTotal() {
        return this.typeSize() + this.backup.typeSize();
    }
}