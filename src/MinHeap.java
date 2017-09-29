import java.util.NoSuchElementException;
/**
 * Your implementation of a min heap.
 *
 * @author Ahmed Gedi
 * @userid agedi3
 * @GTID 903197142
 * @version 1.44
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T item) {
        checkIllegalArgumentException(item);

        if (size  == backingArray.length - 1) {
            resizeBackingArray();
        }

        if (isEmpty()) {
            backingArray[1] = item;
            size++;
        } else {
            int index = size + 1;
            backingArray[index] = item;
            int top = index / 2;
            while (top >= 1
                    && backingArray[top].compareTo(item) > 0) {
                T temp = backingArray[top];
                backingArray[top] = backingArray[index];
                backingArray[index] = temp;
                index = top;
                top = top / 2;
            }
            size++;
        }
//        int itemPos = size;
//        backingArray[itemPos] = item;
//        for (int i = size / 2; i > 0; i = i / 2) {
//            if (item.compareTo(backingArray[i]) > 0) {
//                T obj = backingArray[i];
//                backingArray[i] = item;
//                backingArray[itemPos] = obj;
//                itemPos = i;
//            } else {
//                i = 0;
//            }
//        }
//        System.out.println(backingArray.length);
//        size++;
    }

    /**
     *
     */
    private void resizeBackingArray() {
        T[] tempArr = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i < backingArray.length; i++) {
            tempArr[i] = backingArray[i];
        }
        backingArray = tempArr;

    }

    @Override
    public T remove() {
        checkNoSuchElementException();

        T returnVar = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        int top = 1;
        boolean swap = true;
        int i = 0;
        while (top <= size / 2 && swap) {
            if (top * 2 > size) {
                swap = false;
                return returnVar;
            } else if (top * 2 + 1 > size) {
                i = top * 2;
            } else {
                T left = backingArray[top * 2];
                T right = backingArray[top * 2 + 1];
                if (right.compareTo(left) > 0) {
                    i = top * 2;
                } else {
                    i = top * 2 + 1;
                }
            }
            if (backingArray[top].compareTo(backingArray[i])
                    > 0) {
                T temp = backingArray[top];
                backingArray[top] = backingArray[i];
                backingArray[i] = temp;
                top = i;
                swap = true;


            } else {
                swap = false;
            }
        }
        return returnVar;

//        T obj = backingArray[1];
//        backingArray[1] = backingArray[size - 1];
//        backingArray[size - 1] = null;
//        size--;
//        int atLoc = 1;
//        for (int i = 2; i < size; i = i * 2) {
//            int biggerChild;
//            if (backingArray[i + 1] != null) {
//                biggerChild = backingArray[i].compareTo(backingArray[i + 1]) > 0 ? i : i + 1;
//            } else {
//                biggerChild = i;
//            }
//            if (backingArray[atLoc].compareTo(backingArray[biggerChild]) < 0) {
//                T temp = backingArray[atLoc];
//                backingArray[atLoc] = backingArray[biggerChild];
//                backingArray[biggerChild] = temp;
//                atLoc = biggerChild;
//            } else {
//                i = size;
//            }
//        }
//        return obj;
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * 
     * @param item
     */
    private void checkIllegalArgumentException(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
    }

    private void checkNoSuchElementException() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
    }
}
