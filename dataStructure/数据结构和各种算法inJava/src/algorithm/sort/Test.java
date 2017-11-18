package algorithm.sort;

public class Test {

	public static void main(String[] args) {
		Integer[] array={ 13,34,54,42,36,8,2,644 };
		new InsertSort().sort(array);
		//new BubbleSort().sort(array);
		//new SelectionSort().sort(array);
		//new MergeSort().sort(array);
		//new QuickSort().sort(array);
		printArray(array);
		/*Integer[] a=test(1);
		System.out.println(a[0]);*/
	}
	
	public static <T> void printArray(T[] array){
		System.out.print("[");
		for(int i=0;i<array.length-1;i++){
			System.out.print(array[i]+",");
		}
		System.out.println(array[array.length-1]+"]");
	}
	
	/*public static <T> T[] test(T a){
		T[] array=(T[])new Object[10];
		array[0]=a;
		return array;
	}*/
}
