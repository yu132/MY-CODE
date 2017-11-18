package algorithm.search.interfaces;

import java.util.List;

import algorithm.search.util.Side;

public interface WeightedSearchable<I,W extends Comparable<W>> extends Searchable<I>{

	public List<Side<I,W>> getVertexSide(I fromIndex);
	
}
