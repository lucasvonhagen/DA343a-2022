package laboration1;

public class ArrayQueue<T> implements Queue<T>{
    private T[] elements;
    private int size = 0;

    public ArrayQueue(int maxelements){
        elements = (T[])new Object[maxelements];
    }

    @Override
    public void add(T elem) {
        if (size<elements.length){
            throw new QueueException("enqueue: Queue is full");
        }
        elements[size++] = elem;
    }

    @Override
    public T remove() {
        if (size == 0){
            throw new QueueException("enqueue: Queue is empty");
        }
        T value = elements[0];
        size--;
        for (int i = 1; i<size; i++){
            elements[i-1] = elements[i];
        }
        elements[size]=null;
        return value;
    }

    @Override
    public T element() {
        if (size == 0){
            throw new QueueException("peek: Queue is empty");
        }
        return elements[0];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }
}
