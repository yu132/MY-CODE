package data_structures.list.interfaces;

public interface SortedList<E extends Comparable<E>> {

	public E add(E element);
	
	public boolean delete(E element);
	
	public int indexOf(E element);
	
	public E get(int index);

	public boolean isEmpty();
	
	public E getFirst();
	
	public boolean removeFirst();
	
}
