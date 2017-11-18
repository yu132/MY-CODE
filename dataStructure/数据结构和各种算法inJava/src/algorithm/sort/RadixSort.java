package algorithm.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class RadixSort {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <RT extends Enumerable<RT>,T extends RadixType<RT>> void sort(T[] array){
		//获取最大个体的基数层次
		int maxLength=array[0].length();
		for(int i=1;i<array.length;i++){
			int length=array[i].length();
			if(length>maxLength)
				maxLength=length;
		}
		//获取每个对象的基数分解
		Map<T,List<RT>> radixMap=new HashMap<>();
		for(int i=0;i<array.length;i++){
			radixMap.put(array[i], array[i].getRadixList(maxLength));
		}
		//总排列的链表
		List<T> sortList=new LinkedList<T>();
		for(int i=0;i<array.length;i++){
			sortList.add(i,array[i]);
		}
		//生成暂存排序的箱子
		List[] boxes=new LinkedList[radixMap.get(array[0]).get(0).getEnumNumber()];
		for(int i=0;i<boxes.length;i++)
			boxes[i]=new LinkedList<>();
		//基数排序
		for(int i=maxLength-1;i>=0;i--){
			for(int j=0;j<boxes.length;j++){
				boxes[j].clear();
			}
			for(int j=0;j<sortList.size();j++){
				T temp=sortList.get(j);
				boxes[radixMap.get(temp).get(i).getEnumSortIndex()].add(temp);
			}
			sortList.clear();
			for(int j=0;j<boxes.length;j++)
				sortList.addAll(boxes[j]);
		}
		ListIterator lter=sortList.listIterator();
		for(int i=0;i<array.length;i++){
			array[i]=(T) lter.next();
		}
	}
	
	interface RadixType<RT>{
		public List<RT> getRadixList(int length);
		public int length();
	}
	
	interface Enumerable<RT>{
		public int getEnumNumber();
		public int getEnumSortIndex();
	}
	
	static class TestRadix implements Enumerable<TestRadix>{
		private int data;
		@Override
		public int getEnumNumber() {
			return 10;
		}

		@Override
		public int getEnumSortIndex() {
			return data;
		}

		public TestRadix(int data) {
			if(data<0||data>9)
				throw new IllegalArgumentException();
			this.data = data;
		}

		@Override
		public String toString() {
			return ""+data;
		}
		
		
	}
	
	static class MyInteger implements RadixType<TestRadix>{
		private int data;
		
		@Override
		public List<TestRadix> getRadixList(int length) {
			int datacopy=data;
			List<TestRadix> list=new ArrayList<>();
			for(int i=0;i<length;i++){
				list.add(0,new TestRadix(datacopy%(int)10));
				datacopy/=10;
			}
			return list;
		}

		@Override
		public int length() {
			return Integer.toString(data).length();
		}

		public MyInteger(int data) {
			super();
			this.data = data;
		}

		@Override
		public String toString() {
			return ""+data;
		}
		
	}
	
	public static void main(String[] args) {
		MyInteger[] array=new MyInteger[10];
		array[0]=new MyInteger(113);
		array[1]=new MyInteger(102);
		array[2]=new MyInteger(13);
		array[3]=new MyInteger(210);
		array[4]=new MyInteger(130);
		array[5]=new MyInteger(5);
		array[6]=new MyInteger(140);
		array[7]=new MyInteger(30);
		array[8]=new MyInteger(23);
		array[9]=new MyInteger(23);
		new RadixSort().sort(array);
		printArray(array);
	}
	
	public static <T> void printArray(T[] array){
		System.out.print("[");
		for(int i=0;i<array.length-1;i++){
			System.out.print(array[i]+",");
		}
		System.out.println(array[array.length-1]+"]");
	}
}
