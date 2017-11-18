package data_structures.list;

import data_structures.list.interfaces.SortedList;

public class SortedArrayList<E extends Comparable<E>> extends MyArrayList<E> implements SortedList<E>{

	@Override
	public E add(E element) {
		int index=findInsertIndex(element,0,this.size());
//		System.out.println(this);
		this.insert(index, element);
		return element;
	}
	
	@Override
	public E getFirst() {
		if(size()==0)
			return null;
		else
			return get(0);
	}

	@Override
	public boolean removeFirst() {
		if(size()==0)
			return false;
		delete(get(0));
		return true;
	}

	
	private int findInsertIndex(E value, int left,
			int right) {
		if (left >= right)
			return left;
		int middleIndex = (right - left) / 2 + left;
		E middleValue = this.get(middleIndex);
		if (right - left < 2) {
			if (value.compareTo(this.get(right - 1)) > 0)
				return right;
			else if (value.compareTo(this.get(left)) > 0)
				return left + 1;
			else {
				return left;
			}
		} else if (middleValue.compareTo(value) < 0) {
			return findInsertIndex(value, middleIndex + 1, right);
		} else if (middleValue.compareTo(value) > 0) {
			return findInsertIndex(value, left, middleIndex);
		} else {
			return middleIndex + 1;
		}
	}

	public static void main(String[] args) {
		SortedList<Integer> st=new SortedArrayList<>();
		st.add(123);
		st.add(12);
		st.add(1223);
		st.add(122);
		st.add(113);
		st.add(453);
		st.add(12123);
		st.add(1);
		st.add(22);
		System.out.println(st);
	}
	
}
