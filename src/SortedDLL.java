import java.lang.reflect.TypeVariable;
import java.util.Arrays; // This is only used to print the Array in the main

import static java.lang.reflect.TypeVariable.*;

class SortedDLLNode<T extends Comparable<T>> {
    public T info;
    public SortedDLLNode<T> next, prev;

    public SortedDLLNode() {
        next = null;
        prev = null;
    }

    public SortedDLLNode(T el) {
        info = el;
        next = null;
        prev = null;
    }

    public SortedDLLNode(T el, SortedDLLNode<T> n, SortedDLLNode<T> p) {
        info = el;
        next = n;
        prev = p;
    }
}

public class SortedDLL<T extends Comparable<T>> {
    private SortedDLLNode<T> head, tail;

    public SortedDLL() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void printAll() {
        for (SortedDLLNode<T> tmp = head; tmp != null; tmp = tmp.next)
            System.out.print(tmp.info + " ");
        System.out.println();
    }

    public void insert(T el) {
        if (isEmpty()) {
            head = new SortedDLLNode<>(el);
            tail= head;
            return;
        }
        SortedDLLNode<T> current = head;
        SortedDLLNode<T> toBeInserted = new SortedDLLNode<>(el);

        while (current != null) {
            if (0 > current.info.compareTo(el) && 0 < current.next.info.compareTo(el) ) { //works for numbers bigger than the initiater
                //System.out.println("Im here1");

                if (current.next != null) {
                    SortedDLLNode<T> temp = current.next;
                    toBeInserted.next = temp;
                    current.next = toBeInserted;
                    toBeInserted.prev = current;

                    return;
                } else {
                    SortedDLLNode<T> temp = current;
                    toBeInserted.prev = temp;
                    current.next = toBeInserted;
                    return;
                }
            } else if (0 > current.info.compareTo(el)) {
                current = current.next;
                if (current.next != null) {
                    if (0 > current.next.info.compareTo(el)) {
                        current = current.next;
                    }
                }
                if (current.next != null) {
                    SortedDLLNode<T> temp = current.next;
                    toBeInserted.next = temp;
                    current.next = toBeInserted;
                    toBeInserted.prev = current;

                    return;
                } else {
                    SortedDLLNode<T> temp = current;
                    toBeInserted.prev = temp;
                    current.next = toBeInserted;
                    return;
                }



            } else if (0 == current.info.compareTo(el)) {//just add next to the duplicate number
                current = current.next;
                if (current.next != null) {
                    SortedDLLNode<T> temp = current.next;
                    toBeInserted.next = temp;
                    current.next = toBeInserted;
                    toBeInserted.prev = current;

                    return;
                } else {
                    SortedDLLNode<T> temp = current;
                    toBeInserted.prev = temp;
                    current.next = toBeInserted;
                    return;
                }

            } else if ((0 <current.info.compareTo(el)) ) { //smaller than the initiater - works
                //System.out.println("Im here3");


                if (current == head) {
                    current.prev= toBeInserted;
                    head =toBeInserted;
                    toBeInserted.next=current;
                    toBeInserted.prev=null;

                }else{
                    SortedDLLNode<T> temp= current.prev;
                    toBeInserted.prev=temp;
                }
                current.prev=toBeInserted;
                toBeInserted.next=current;
                return;


            } else {
                //System.out.println("Im here4");
                current = current.next;
                return;
            }
        }

    }

    public static void main(String[] args) {

        SortedDLL<Integer> myList = new SortedDLL<Integer>();
        Integer[] numberList = { 5, 4, 10, 2, 7, 3 };
        System.out.println("We would like to insert the following array to the sorted linked list: "
                + Arrays.toString(numberList));
        for (int i = 0; i < numberList.length; i++) {
            myList.insert(numberList[i]);
            System.out.println("The List after inserting the element " + numberList[i] + " is: ");
            myList.printAll();
        }
    }

}
