package laboration2;

import java.util.LinkedList;


public class LinkedQueue<T> implements Queue<T> {
	private LinkedList<T> queue = new LinkedList<T>();
	public void add(T element) {
		queue.addLast(element);
	}
	public T remove() {
		if(queue.size()==0)
			throw new QueueException("dequeue: Queue is empty");
		return queue.removeFirst();
	}

	public T element() {
		if(queue.size()==0)
			throw new QueueException("peek: Queue is empty");
		return queue.getFirst();
	}

	public boolean isEmpty() {
		return (queue.size()==0);
	}

	public int size() {
		return queue.size();
	}
}