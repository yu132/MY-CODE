package algorithm.sort;

import java.util.Comparator;

import algorithm.sort.interfaces.Sorter;

public class BubbleSort implements Sorter{

	@Override
	public <T> void sort(T[] array, Comparator<T> comparator) {
		for(int i=0;i<array.length-1;i++){
			for(int j=0;j<array.length-i-1;j++){
				if(comparator.compare(array[j], array[j+1])>0){
					T temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
	}

	@Override
	public <T extends Comparable<? super T>> void sort(T[] array) {
		for(int i=0;i<array.length-1;i++){
			for(int j=0;j<array.length-i-1;j++){
				if(array[j].compareTo(array[j+1])>0){
					T temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
	}

}
