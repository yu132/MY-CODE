package data_structures.list.interfaces;

public interface LinearList<E> {

	public boolean isEmpty();
	
	public int size();
	
	public E get(int index);
	
	public boolean delete(E element);
	
	public int indexOf(E element);
	
	public E insert(int index,E e);
	
}
