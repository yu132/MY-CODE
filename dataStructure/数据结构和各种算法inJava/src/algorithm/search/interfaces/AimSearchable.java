package algorithm.search.interfaces;

import java.util.List;

public interface AimSearchable<I> extends Searchable<I>{
	
	public I getAimIndex();
	
	public List<I> getBackIndexes();

}
