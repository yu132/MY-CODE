package algorithm.sort.interfaces;

import java.util.Comparator;

public interface Sorter {

	public<T> void sort(T[] array,Comparator<T> comparator);
	
	public<T extends Comparable<? super T>> void sort(T[] array);
}
