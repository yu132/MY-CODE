package data_structures.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sparse_matrix<E> {

	private List<Unit<E>> list;
	
	private int size;
	
	public Sparse_matrix(int size){
		this.size=size;
		list=new ArrayList<>();
	}
	
	public Sparse_matrix(int size,int listSize){
		this.size=size;
		list=new ArrayList<>(listSize);
	}
	
	public void set(int row,int col,E e){
		if(row>size-1||row<0||col>size-1||col<0)
			throw new IndexOutOfBoundsException();
		insertOrSet(list,new Unit<E>(row,col,e),0,list.size());
	}
	
	public E get(int row,int col){
		if(row>size-1||row<0||col>size-1||col<0)
			throw new IndexOutOfBoundsException();
		int index=Collections.binarySearch(list, new Unit<E>(row,col,null));
		return list.get(index).getData();
	}
	
	public void add(Sparse_matrix<E> sparse_matrix,Adder<E> adder){
		int index1=0;
		int index2=0;
		while(index1!=this.list.size()-1&&index2!=sparse_matrix.list.size()-1){
			int ans=this.list.get(index1).compareTo(sparse_matrix.list.get(index2));
			if(ans>0){
				Unit<E> temp=sparse_matrix.list.get(index2);
				this.list.add(index1, new Unit<E>(temp.getRow(),temp.getCol(),temp.getData()));
				index2++;
			}else if(ans<0)
				index1++;
			else{
				this.list.get(index1).setData(adder.add(this.list.get(index1).getData(), sparse_matrix.list.get(index2).getData()));
				index1++;
				index2++;
			}
		}
		while(index2!=sparse_matrix.list.size()-1){
			Unit<E> temp=sparse_matrix.list.get(index2);
			this.list.add(new Unit<E>(temp.getRow(),temp.getCol(),temp.getData()));
		}
	}
	
	public void transpose(){
		List<Unit<E>> tempList=new ArrayList<>();
		int[] eachRow=new int[size];
		for(int i=0;i<size;i++)
			eachRow[i]=-1;
		for(int i=0,row=-1;i<list.size();i++){
			if(list.get(i).getRow()>row){
				row=list.get(i).getRow();
				eachRow[row]=i;
			}
		}
		int[] eachRowCopy=new int[size];
		System.arraycopy(eachRow, 0, eachRowCopy, 0, size);
		for(int x=0;x<list.size();x++){
			int minRow;
			for(minRow=0;minRow<size;minRow++){
				if(eachRowCopy[minRow]==-1)
					continue;
				int tempi=minRow+1;
				while(tempi<size&&eachRowCopy[tempi]==-1)
					tempi++;
				if(tempi==size){
					tempi--;
					if(eachRow[minRow]>=list.size())
						continue;
					else
						break;
				}
				if(eachRow[minRow]<eachRowCopy[tempi]){
					break;
				}
			}
			if(minRow==size)
				break;
			for(int i=minRow+1;i<size;i++){
				int tempi=i+1;
				while(tempi<size&&eachRowCopy[tempi]==-1)
					tempi++;
				if(tempi==size){
					if(eachRow[i]>=list.size())
						continue;
					else{
						if(list.get(eachRow[i]).getCol()<list.get(eachRow[minRow]).getCol())
							minRow=i;
						continue;
					}
				}
				if(eachRow[i]<eachRowCopy[tempi]){
					if(eachRow[i]!=-1){
						if(list.get(eachRow[i]).getCol()<list.get(eachRow[minRow]).getCol())
							minRow=i;
					}
				}
			}
			Unit<E> temp=list.get(eachRow[minRow]);
			tempList.add(new Unit<E>(temp.getCol(),temp.getRow(),temp.getData()));
			eachRow[minRow]++;
		}
		list=tempList;
	}
	
	
	@Override
	public String toString() {
		return list.toString();
	}

	private static <T extends Comparable<? super T>> void insertOrSet(List<T> sortList, T value, int left, int right) {
		System.out.println(left+" "+right);
		if (left >= right){
			sortList.add(left, value);
			return;
		}
		int middleIndex = (right - left) / 2 + left;
		T middleValue = sortList.get(middleIndex);
		if (right - left < 2) {
			if (value.compareTo(sortList.get(right - 1)) > 0){
				sortList.add(right, value);
				return;
			}
			else if (value.compareTo(sortList.get(left)) > 0){
				sortList.add(left + 1, value);
				return;
			}
			else {
				sortList.add(left, value);
				return;
			}
		} else if (middleValue.compareTo(value) < 0) {
			insertOrSet(sortList, value, middleIndex + 1, right);
		} else if (middleValue.compareTo(value) > 0) {
			insertOrSet(sortList, value, left, middleIndex);
		} else {
			sortList.set(middleIndex, value);
			return;
		}
	}
	
	public static void main(String[] args) {
		Sparse_matrix<Integer> sm=new Sparse_matrix<>(5);
		sm.set(0, 0, 100);
		sm.set(1, 2, 123);
		sm.set(1, 4, 1234);
		sm.set(2, 1, 54);
		sm.set(4, 1, 654);
		System.out.println(sm);
		sm.transpose();
		System.out.println(sm);
	}
	
}

interface Adder<E>{
	
	public E add(E e1,E e2);
	
}

class Unit<E> implements Comparable<Unit<E>>{
	
	private int row;
	private int col;
	private E data;
	
	public Unit() {
		super();
	}
	
	public Unit(int row, int col, E data) {
		super();
		this.row = row;
		this.col = col;
		this.data = data;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Unit [row=" + row + ", col=" + col + ", data=" + data + "]";
	}

	@Override
	public int compareTo(Unit<E> o) {
		if(this.row>o.row)
			return 1;
		else if(this.row<o.row)
			return -1;
		else{
			if(this.col>o.col)
				return 1;
			else if(this.col<o.col)
				return -1;
			else return 0;
		}
	}
	
}