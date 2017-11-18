package ui.swingUI.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinaryInsetList {
	//List
	public static<T>  int findInsertIndex(List<T> sortList ,Comparator<T> comparator, T value , int left , int right){
        int middleIndex = (right - left) / 2 + left  ;
        T middleValue = sortList.get(middleIndex);
        if(right - left < 2){
        	if(comparator.compare(value, sortList.get(right -1))>0)
        		return right;
        	else if(comparator.compare(value, sortList.get(left))>0)
                return left + 1;
            else{
                return left;
            }
        }else if(comparator.compare(middleValue, value)<0){
            return findInsertIndex(sortList ,comparator, value , middleIndex + 1 , right);
    	}else if(comparator.compare(middleValue, value)>0){
            return findInsertIndex(sortList ,comparator, value , left , middleIndex );
        }else{
            return middleIndex + 1;
        }
    }
    
    public static <T extends Comparable<? super T>>  int findInsertIndex(List<T> sortList , T value , int left , int right){
    	if(left==right)
    		return left;
        int middleIndex = (right - left) / 2 + left ;
        T middleValue = sortList.get(middleIndex);
        if(right - left < 2){
        	if(value.compareTo(sortList.get(right -1))>0)
        		return right;
        	else if(value.compareTo(sortList.get(left))>0)
                return left + 1;
            else{
                return left;
            }
        }else if(middleValue.compareTo(value)<0){
            return findInsertIndex(sortList , value , middleIndex + 1 , right);
    	}else if(middleValue.compareTo(value)>0){
            return findInsertIndex(sortList , value , left , middleIndex );
        }else{
            return middleIndex + 1;
        }
    }
    
    public static <T extends Comparable<? super T>> int binaryInsert(List<T> list , T value){
  /*  	if(value.compareTo(list.get(list.size()-1))>0){
    		list.add(value);
    		return list.size()-1;
    	}*/
    	//if(list.size()!=0){
	    	int index=findInsertIndex(list,value,0,list.size());
	    	list.add(index,value);
	    	return index;
    	/*}else{
    		list.add(value);
    		return 0;
    	}*/
    }
    //数组
    public int[] insert(int[] sortArray , int value){
        int index = findInsertIndex(sortArray , value , 0 , sortArray.length); //找到插入位置
        int[] newArray = new int[sortArray.length + 1]; 
        newArray[index] = value ;
        System.arraycopy(sortArray, 0, newArray, 0 , index );
        System.arraycopy(sortArray, index, newArray, index + 1 , sortArray.length - index);
        return newArray ;    //返回新数组
    }
  
    //找到value适合插入的位置
    public int findInsertIndex(int[] sortArray , int value , int left , int right){
        int middleIndex = (right - left) / 2 + left  ;
        int middleValue = sortArray[middleIndex] ;
        if(right - left < 2){
            if(value > sortArray[right -1 ])
                return right;
            else if(value > sortArray[left]){
                return left + 1;
            }else{
                return left;
            }
        }else if(middleValue < value){
            return findInsertIndex(sortArray , value , middleIndex + 1 , right);
        }else if(middleValue > value){
            return findInsertIndex(sortArray , value , left , middleIndex );
        }else{
            return middleIndex + 1;
        }
    }
  

    
    public static void main(String[] args) {
		List<Integer> list=new ArrayList<>();
		/*list.add(18);
		list.add(27);
		list.add(35);
		//list.add(4);
		list.add(57);
		list.add(65);
		list.add(87);
		list.add(89);
		list.add(95);
		list.add(140);*/
		
		//int index=findInsertIndex(list,6,0,list.size()-1);
		System.out.println(binaryInsert(list,41));
		System.out.println(list);
		System.out.println(binaryInsert(list,42));
		System.out.println(list);
		System.out.println(binaryInsert(list,43));
		System.out.println(list);
		System.out.println(binaryInsert(list,46));
		System.out.println(list);
		System.out.println(binaryInsert(list,44));
		System.out.println(list);
		System.out.println(binaryInsert(list,45));
		System.out.println(list);
		System.out.println(binaryInsert(list,1));
		System.out.println(list);
		//binaryInsert(list,166);
	}
}
