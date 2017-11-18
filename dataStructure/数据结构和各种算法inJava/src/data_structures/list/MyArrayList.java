package data_structures.list;

import data_structures.list.interfaces.LinearList;

public class MyArrayList<E> implements LinearList<E>{

	private Object[] elementList;
	
	private int size;
	
	public MyArrayList() {
		elementList=new Object[10];
	}

	public MyArrayList(int size) {
		elementList=new Object[size];
	}

	private void extend(int size){
		if(size<=elementList.length)
			return;
		Object[] temp=new Object[size];
		System.arraycopy(elementList, 0, temp, 0, this.size);
		elementList=temp;
	}
	
	@Override
	public boolean isEmpty() {
		if(size==0)
			return true;
		else
			return false;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if(index<0||index>size)
			throw new IndexOutOfBoundsException();
		return (E)elementList[index];
	}

	@Override
	public boolean delete(E element) {
		for(int i=0;i<size;i++){
			if(elementList[i].equals(element)){
				while(i<size){
					elementList[i]=elementList[i+1];
					i++;
				}
				size--;
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(E element) {
		for(int i=0;i<size;i++){
			if(elementList[i].equals(element))
				return i;
		}
		return -1;
	}

	@Override
	public E insert(int index, E element) {
		if(size+1==elementList.length)
			extend(elementList.length+10);
		for(int i=size;i>index;i--){
			elementList[i]=elementList[i-1];
		}
		elementList[index]=element;
		size++;
		return element;
	}

	@Override
	public String toString() {
		if(size==0)
			return "[]";
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		for(int i=0;i<size-1;i++){
			sb.append(elementList[i]+",");
		}
		sb.append(elementList[size-1]+"]");
		return sb.toString();
	}

	public static void main(String[] args) {
		MyArrayList<Integer> list=new MyArrayList<>();
		list.insert(0, 1);
		list.insert(1, 4);
		list.insert(0, 2);
		list.insert(1, 5);
		list.insert(0, 7);
		list.insert(1, 9);
		//list.print();
		System.out.println(list.indexOf(4));
		list.delete(2);
		System.out.println(list.get(4));
		
	}

}
