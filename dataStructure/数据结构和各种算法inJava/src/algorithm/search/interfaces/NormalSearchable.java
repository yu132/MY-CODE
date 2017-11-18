package algorithm.search.interfaces;

import java.util.List;

public interface NormalSearchable<I> extends Searchable<I>{

	public List<I> getAdjacentIndexes(I index);
	
}
