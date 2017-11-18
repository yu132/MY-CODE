package algorithm.sort;

import java.util.Comparator;

import algorithm.sort.interfaces.Sorter;

public class InsertSort implements Sorter{

	@Override
	public <T> void sort(T[] array, Comparator<T> comparator) {
		for(int i=1;i<array.length;i++){
			T temp=array[i];
			int j=0;
			while(comparator.compare(array[j], temp)<0&&j<=i)
				j++;
			for(int x=i;x>j;x--)
				array[x]=array[x-1];
			array[j]=temp;
		}
	}
	
	@Override
	public <T extends Comparable<? super T>> void sort(T[] array) {
		for(int i=1;i<array.length;i++){
			T temp=array[i];
			int j=0;
			while(array[j].compareTo(temp)<0&&j<=i)
				j++;
			for(int x=i;x>j;x--)
				array[x]=array[x-1];
			array[j]=temp;
		}
	}


}
