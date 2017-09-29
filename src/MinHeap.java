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
        checkToResize();
        if (isEmpty()) {
            addToFront(item);
        } else {
            int itemPos = size + 1;
            backingArray[itemPos] = item;
            int top = itemPos / 2;
            while (top >= 1 && backingArray[top].compareTo(item) > 0) {
                T temp = backingArray[top];
                backingArray[top] = backingArray[itemPos];
                backingArray[itemPos] = temp;
                itemPos = top;
                top = top / 2;
            }
            size++;
        }
    }

    @Override
    public T remove() {
        checkNoSuchElementException();
        T returnVar = intialStepsBeforeRemove();
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
            if (backingArray[top].compareTo(backingArray[i]) > 0) {
                top = getTop(top, i);
                swap = true;
            } else {
                swap = false;
            }
        }
        return returnVar;
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
     * @param item the item to check if it is null or not
     */
    private void checkIllegalArgumentException(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
    }

    /**
     * throws a NoSuchElementException
     */
    private void checkNoSuchElementException() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
    }

    /**
     * neccesary steps before remove
     * @return value to be removed
     */
    private T intialStepsBeforeRemove() {
        T returnVar = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        return returnVar;
    }

    /**
     * check to resize
     */
    private void checkToResize() {
        if (size  == backingArray.length - 1) {
            resizeBackingArray();
        }
    }

    /**
     *
     * @param item the data to be added to the front if array is empty
     */
    private void addToFront(T item) {
        backingArray[1] = item;
        size++;
    }

    /**
     * if needed then the backingarray will regrow
     */
    private void resizeBackingArray() {
        T[] newArr = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i < backingArray.length; i++) {
            newArr[i] = backingArray[i];
        }
        backingArray = newArr;

    }

    /**
     *
     * @param top the top int variable
     * @param i the int i
     * @return the top variable
     */
    private int getTop(int top, int i) {
        T temp = backingArray[top];
        backingArray[top] = backingArray[i];
        backingArray[i] = temp;
        top = i;
        return top;
    }
}
