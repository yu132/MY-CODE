package algorithm.search.util;

public class Counter {
	int count;
	public void countDown(){
		if(count<=0)
			throw new RuntimeException();
		count--;
	}
	public boolean isZero(){
		return count==0;
	}
	public Counter(int count) {
		this.count = count;
	}
}
