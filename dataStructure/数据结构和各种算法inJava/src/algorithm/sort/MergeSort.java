package algorithm.sort;

import java.util.Comparator;

import algorithm.sort.interfaces.Sorter;

public class MergeSort implements Sorter{

	@Override
	public <T> void sort(T[] array, Comparator<T> comparator) {
		mergeSort(array,0,array.length,comparator);
	}

	@Override
	public <T extends Comparable<? super T>> void sort(T[] array) {
		mergeSort(array,0,array.length);
	}
	
	private <T extends Comparable<? super T>> void mergeSort(T[] a,int start,int end){
		if(start>=end-1)
			return;
		int mid=(start+end)/2;
		mergeSort(a,start,mid);
		mergeSort(a,mid,end);
		_2wayMerging(a,start,mid,end);
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Comparable<? super T>> void _2wayMerging(Object[] a,int start,int mid,int end){
		Object[] temp=new Object[end-start];
		int i=start,j=mid,k=0;
		while(i!=mid&&j!=end){
			if(((T)a[i]).compareTo((T)a[j])<=0)
				temp[k++]=a[i++];
			else
				temp[k++]=a[j++];
		}
		if(i==mid)
			while(j!=end)
				temp[k++]=a[j++];
		else if(j==end)
			while(i!=mid)
				temp[k++]=a[i++];
		for(i=start,j=0;j<temp.length;i++,j++)
			a[i]=temp[j];
	}
	
	private <T> void mergeSort(T[] a,int start,int end,Comparator<T> comparator){
		if(start>=end-1)
			return;
		int mid=(start+end)/2;
		mergeSort(a,start,mid,comparator);
		mergeSort(a,mid,end,comparator);
		_2wayMerging(a,start,mid,end,comparator);
	}
	
	@SuppressWarnings("unchecked")
	private <T> void _2wayMerging(Object[] a,int start,int mid,int end,Comparator<T> comparator){
		Object[] temp=new Object[end-start];
		int i=start,j=mid,k=0;
		while(i!=mid&&j!=end){
			if(comparator.compare((T)a[i], (T)a[j])<=0)
				temp[k++]=a[i++];
			else
				temp[k++]=a[j++];
		}
		if(i==mid)
			while(j!=end)
				temp[k++]=a[j++];
		else if(j==end)
			while(i!=mid)
				temp[k++]=a[i++];
		for(i=start,j=0;j<temp.length;i++,j++)
			a[i]=temp[j];
	}

}
