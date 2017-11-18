package algorithm.sort;

import java.util.Comparator;

import algorithm.sort.interfaces.Sorter;

public class QuickSort implements Sorter{

	@Override
	public <T> void sort(T[] array, Comparator<T> comparator) {
		quickSort(array,0,array.length-1,comparator);
	}

	@Override
	public <T extends Comparable<? super T>> void sort(T[] array) {
		quickSort(array,0,array.length-1);
	}
	
	private <T extends Comparable<? super T>> void quickSort(T[] array,int begin,int end){
		if (begin < end) {
			int q = Partition(array, begin, end);
			quickSort(array, begin, q - 1);
			quickSort(array, q + 1, end);
		}
	}
	
	private <T extends Comparable<? super T>> int Partition(T[] array,int begin,int end){
		T x=array[begin];
		int i=begin,j=end+1;
		while(true){
			while(x.compareTo(array[--j])<0&&i<j);
			while(x.compareTo(array[++i])>0&&i<j);
			if(i>=j)
				break;
			T temp=array[i];
			array[i]=array[j];
			array[j]=temp;
		}
		array[begin]=array[j];
		array[j]=x;
		return j;
	}
	
	private <T> void quickSort(T[] array,int begin,int end, Comparator<T> comparator){
		if (begin < end) {
			int q = Partition(array, begin, end, comparator);
			quickSort(array, begin, q - 1, comparator);
			quickSort(array, q + 1, end, comparator);
		}
	}
	
	private <T> int Partition(T[] array,int begin,int end, Comparator<T> comparator){
		T x=array[begin];
		int i=begin,j=end+1;
		while(true){
			while(comparator.compare(x, array[--j])<0&&i<j);
			while(comparator.compare(x, array[++i])>0&&i<j);
			if(i>=j)
				break;
			T temp=array[i];
			array[i]=array[j];
			array[j]=temp;
		}
		array[begin]=array[j];
		array[j]=x;
		return j;
	}

}
