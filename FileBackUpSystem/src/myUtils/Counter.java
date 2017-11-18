package myUtils;

public class Counter {

	private int count;
	private int all;

	public Counter(int count) {
		this.count = count;
		all=count;
	}
	
	public void reset(){
		count=all;
	}
	
	public int getAll() {
		return all;
	}

	public int getCount() {
		return count;
	}

	public void countDown(){
		if(count<=0)
			throw new OutOfBoundsException("Count can't be less than 0");
		count--;
	}
	
	public boolean isZero(){
		if(count==0)
			return true;
		else
			return false;
	}
	
	class OutOfBoundsException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		OutOfBoundsException(){}
		public OutOfBoundsException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
		public OutOfBoundsException(String message, Throwable cause) {
			super(message, cause);
		}
		public OutOfBoundsException(String message) {
			super(message);
		}
		public OutOfBoundsException(Throwable cause) {
			super(cause);
		}
		
	}
}
