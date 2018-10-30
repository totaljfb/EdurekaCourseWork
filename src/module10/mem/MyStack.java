package module10.mem;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyStack<E> implements Iterable<E> {
	protected E[] data = null;
	protected int stack_pointer = 0;
	
	@SuppressWarnings("unchecked")
	public MyStack(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public void push(E element) throws MyStackException{
		if (stack_pointer == data.length) {
			throw new MyStackException("Stack overflow");
		}
		data[stack_pointer++] = element;
	}
	
	public boolean isEmpty() {
		return stack_pointer == 0;
	}
	
	public E pop() throws MyStackException{
		if (stack_pointer == 0) {
			throw new MyStackException("Stack underflow");
		}
		E e = data[--stack_pointer];
		data[stack_pointer] = null;
		return e;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>(){
			private int i = 0;
			@Override
			public E next() {
				if(i> stack_pointer)
					throw new NoSuchElementException("No element to retrieve");
				return data[i++];	
			}
			@Override
			public boolean hasNext() {
				return stack_pointer >= 0;
			}

			};
	}

}

class MyStackException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5710437451844911295L;
	public MyStackException(String message) {
		super(message);
	}
	
}

