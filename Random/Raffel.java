package Random;

import java.util.*;

/**
 * A Raffle allows you to insert objets and then randomly call them.
 * Afer calling the object, it is removed.
 * @param <T> objets to be randomly generated.
*/
public class Raffel<T>{
    private final boolean DEBUG = false;
    private final HashMap<T, Integer> NODEINDEX;
    private final ArrayList<Node<T>> NODES;
    private int size;
    private static final Random random = new Random();
    /**
     * Creates an empty Raffel
     */
    public Raffel(){
        this.NODEINDEX = new HashMap<>();
        this.NODES = new ArrayList<>();
        this.size = 0;
        this.checkRep();
    }
    /**
     * Creates a Raffel with all elements in 'elements'.
     */
    public Raffel(T[] elements){
        this.NODEINDEX = new HashMap<>();
        this.NODES = new ArrayList<>();
        this.size = 0;
        for(T element: elements){
            this.add(element);
        }
        this.checkRep();
    }
    /**
     * Creates a Raffel with all elements in 'elements'.
     */
    public Raffel(List<T> elements){
        this.NODEINDEX = new HashMap<>();
        this.NODES = new ArrayList<>();
        this.size = 0;
        for(T element: elements){
            this.add(element);
        }
        this.checkRep();
    }
    /**
     * Creates a Raffel with all elements in 'elements'.
     */
    public Raffel(Set<T> elements){
        this.NODEINDEX = new HashMap<>();
        this.NODES = new ArrayList<>();
        this.size = 0;
        for(T element: elements){
            this.add(element);
        }
        this.checkRep();
    }
    /**
     * Add 'element' to the raffel
     * 
     * @param element element to be added
     * @modifies this adds element to this
    */
    public void add(T element){
        this.checkRep();
        add(element, 1);
        this.checkRep();
    }
    /**
     * Add 'amount' 'element's to the raffel
     * 
     * @param element element(s) to be added
     * @param amount number of 'element' to add
     * @throws IllegalArgumentException 'amount' < 0
     * @modifies this adds 'amount' elements to this
    */
    public void add(T element, int amount){
        this.checkRep();
        if(amount == 0){
            return;
        }if(amount < 0){
            throw new IllegalArgumentException();
        }
        if(this.NODEINDEX.containsKey(element)){
            int index = NODEINDEX.get(element);
            NODES.get(index).size += amount;
            adjustParentNode(index, amount);
            this.size += amount;
            this.checkRep();
            return;
        }
        this.NODEINDEX.put(element, NODES.size());
        NODES.add(new Node(element, amount));
        adjustParentNode(NODES.size() - 1, amount);
        this.size += amount;
        this.checkRep();
    }
    /**
     * return a random 'T' with each index weighted by it's size
     * 
     * @return random 'T' with each index weighted by it's size or null iff this is empty
    */
    public T peak(){
        this.checkRep();
        if(this.isEmpty()){
            return null;
        }
        int index = getRandomIndex();
        T goal = NODES.get(index).value;
        this.checkRep();
        return goal;
    }
    /**
     * remove and return a random 'T' with each index weighted by it's size
     * 
     * @modefies 'this' removes value that was returned from 'this'. Iff 'this' is empty, this is not modifies
     * @return random 'T' with each index weighted by it's size or null iff this is empty
    */
    public T pull(){
        this.checkRep();
        // Check is empty
        if(this.isEmpty()){
            return null;
        }
        // Record value
        int index = getRandomIndex();
        Node<T> goal = NODES.get(index);
        T goalValue = goal.value;
        // Remove value
        this.size--;
        this.NODES.get(index).size--;
        this.adjustParentNode(index, -1);
        if(this.NODES.get(index).size != 0){
            this.checkRep();
            return goalValue;
        }
        // Remove empty node
        // *record last node
        int finalIndex = this.NODES.size() - 1;
        Node<T> finalNode = this.NODES.get(finalIndex);
        // *copy last node info to removed node
        goal.value = finalNode.value;
        goal.size = finalNode.size;
        this.adjustParentNode(index, finalNode.size);
        this.NODEINDEX.put(finalNode.value, index);
        this.NODEINDEX.remove(goalValue);
        // *remove final node
        this.adjustParentNode(finalIndex, -finalNode.size);
        this.NODES.remove(finalIndex);
        this.checkRep();
        return goalValue;
    }
    /**
     * remove and return a list of 'numOfItems' random 'T's with each index weighted by it's size
     * 
     * @param numOfItems numOf T to be printed
     * @modefies 'this' removes values that was returned from 'this'.
     * @throws Exception Iff numOfItems > size or numOfItems < 0.
     * @return random 'T' with each index weighted by it's size or null iff this is empty
    */
    public List<T> pull(int numOfItems){
        this.checkRep();
        if(numOfItems > this.size || numOfItems < 0){
            throw new Error();
        }
        List<T> goal = new ArrayList<>();
        for(int i = 0; i != numOfItems; i++){
            goal.add(this.pull());
        }
        this.checkRep();
        return goal;
    }
    /**
     * return number of items in 'this'
     * 
     * @return number of items in 'this'
    */
    public int size(){
        return this.size;
    }
    /**
     * return number of item types stored in 'this'
     * 
     * @return number of item types stored in 'this'
    */
    public int typeSize(){
        return this.NODES.size();
    }
    /**
     * returns true iff there are no elements in 'this'
     * 
     * @return true iff there are no elements in 'this'
    */
    public boolean isEmpty(){
        return this.NODEINDEX.isEmpty();
    }
    private void checkRep(){
        if(!DEBUG){
            return;
        }
        asert(this.NODEINDEX != null, "this.nodeIndex == null");
        asert(this.NODES != null, "this.NODES == null");
        asert(this.NODEINDEX.size() == this.NODES.size(), "Type size mismatch");
        int size = 0;
        for(T nodeValue: NODEINDEX.keySet()){
            int nodeIndex = NODEINDEX.get(nodeValue);
            Node node = NODES.get(nodeIndex);
            asert(node.size > 0, "Node has none positive size");
            asert(node.leftSize >= 0, "Node has none positive child");
            asert(node.rightSize >= 0, "Node has none positive child");
            asert(node.value.equals(nodeValue), "NODEINDEX leads to incorrect node");
            size += node.size;
        }
        asert(this.size == size, "Nodes do not add up to size");
        }
    private static void asert(boolean asert, String errorMessage){
        if(!asert){
            throw new RuntimeException(errorMessage);
        }
    }
    /**
     * Get a random index with each index weighted by it's size
     * 
     * @requires this is not empty
     * @return random index with each index weighted by it's size
    */
    private int getRandomIndex(){
        int current = 0;
        while(true){
            Node node = NODES.get(current);
            int totalChildSize = node.leftSize + node.rightSize;
            double chanceOfStaying = (node.size * 1.0) / (totalChildSize + node.size);
            if(random.nextDouble() < chanceOfStaying){
                return current;
            }if(random.nextDouble() < (node.leftSize * 1.0) / (totalChildSize)){
                current = firstChild(current);
            }else{
                current = secondChild(current);
            }
        }
        
    }
    /**
     * Find the parent location of the 'child' index
     * 
     * @param child child index of parent we are lookin for
     * @requires 'child' > 0
     * @return parent index of 'child'
    */
    private static int parent(int child){
        return (child - 1)/2;
    }
    /**
     * Find the first child location of the 'parent' index
     * 
     * @param parent parent index of the child we are looking for
     * @requires 'parent' >= 0
     * @return first child index of 'parent'
    */
    private static int firstChild(int parent){
        return parent*2+1;
    }
    /**
     * Find the second child location of the 'parent' index
     * 
     * @param parent parent index of the child we are looking for
     * @requires 'parent' >= 0
     * @return second child index of 'parent'
    */
    private static int secondChild(int parent){
        return firstChild(parent) + 1;
    }
    /**
     * Tell parent and super parents of 'node' one of their childen 'node' has chaned size
     * 
     * @param node index of childNode
     * @param adjustment change in child size
     * @modifies this adjust parent and super parents of node so that they see 'node' has grown by 'adjustment'
     * @requires node >= 0
     */
    private void adjustParentNode(int node, int adjustment){
        if(node == 0){
            return;
        }
        int parent = parent(node);
        Node parentNode = NODES.get(parent);
        int firstChild = firstChild(parent);
        if(firstChild == node){
            parentNode.leftSize += adjustment;
        }else{
            parentNode.rightSize += adjustment;
        }
        adjustParentNode(parent, adjustment);
    }
    /**
     * The 'Node' class keeps track of its index, size, totalLeftSize, TotalRightSize, and what object it stores.
     * @param <T> object type that can be stored in this node
     */
    private class Node<T>{
        T value; // Value stored in node
        int size; // number of 'value' stored here
        int leftSize; // total number of objects stored on left subtree
        int rightSize; // total number of objects stored on right subtree
        Node(T value, int amount){
            this.value = value;
            this.size = amount;
            this.leftSize = 0;
            this.rightSize = 0;
        }
    }
}