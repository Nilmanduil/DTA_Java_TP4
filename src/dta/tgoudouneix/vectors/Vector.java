package dta.tgoudouneix.vectors;

import java.util.Iterator;

public class Vector<T> implements Iterable<T> {
	private Object[] values;
	private int size;
	
	private class VectorIterator<T> implements Iterator<T> {
		private int index = 0;
				
		@Override
		public boolean hasNext() {
			return this.index < Vector.this.size;
		}

		@Override
		public T next() {
			if(this.hasNext()) {
				T current = (T) Vector.this.get(this.index);
				this.index++;
				return current;
			}
			else
				return null;
		}		
	}
	
	public Vector(int size) {
		this.size = size;
		this.values = new Object[size];
	}
	
	public void ensureCapacity(int capacity) {
		if(this.values.length < capacity) {
			int newCapacity = Math.max(capacity, 2 * this.values.length);
			Object[] array = new Object[newCapacity];
			
			for(int i = 0; i < newCapacity; i++) {
				if(i < this.size) {
					array[i] = this.values[i];
				}
				else {
					array[i] = 0;
				}
			}
			
			this.values = array;
		}
	}
	
	public void resize(int size) {
		ensureCapacity(size);
		this.size = size;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public void add(T element) {
		resize(size() + 1);
		set(size() - 1, element);
	}
	
	public void set(int index, T element) {
		if(index < this.size) 
			this.values[index] = element;
	}
	
	public T get(int index) {
		return (index < size ? (T) this.values[index] : (T) new Object());
	}
	
	public VectorIterator<T> iterator() {
		return new VectorIterator();
	}
	
	public void addAll(Vector<T> elements) {
		VectorIterator<T> iterator = elements.iterator();
		
		while(iterator.hasNext()) {
			this.add(iterator.next());
		}
	}
}
