package algorithm.sort;

import java.util.Comparator;

import algorithm.sort.interfaces.Sorter;

public class SelectionSort implements Sorter{

	@Override
	public <T> void sort(T[] array, Comparator<T> comparator) {
		for(int i=0;i<array.length;i++){
			T min=array[i];
			int index=i;
			for(int j=i+1;j<array.length;j++){
				if(comparator.compare(array[j], min)<0){
					index=j;
					min=array[j];
				}
			}
			if(index!=i){
				T temp=array[i];
				array[i]=array[index];
				array[index]=temp;
			}
		}
	}

	@Override
	public <T extends Comparable<? super T>> void sort(T[] array) {
		for(int i=0;i<array.length;i++){
			T min=array[i];
			int index=i;
			for(int j=i+1;j<array.length;j++){
				if(array[j].compareTo(min)<0){
					index=j;
					min=array[j];
				}
			}
			if(index!=i){
				T temp=array[i];
				array[i]=array[index];
				array[index]=temp;
			}
		}
	}

}
