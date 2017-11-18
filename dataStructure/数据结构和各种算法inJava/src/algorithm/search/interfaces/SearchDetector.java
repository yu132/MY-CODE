package algorithm.search.interfaces;

import java.util.List;

public interface SearchDetector<I> {

	public void doWhenSearchthisIndex(I i);
	
	public void doWhenGetnextIndex(List<I> indexes);
	
}
