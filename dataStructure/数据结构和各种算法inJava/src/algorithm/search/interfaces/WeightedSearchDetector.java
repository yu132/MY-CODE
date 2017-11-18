package algorithm.search.interfaces;

import java.util.List;

import algorithm.search.util.Side;

public interface WeightedSearchDetector <I,W extends Comparable<W>>{

	public void doWhenSearchthisIndex(Side<I,W> side);
	
	public void doWhenGetNextIndex(List<Side<I,W>> sides);
	
	public void doWhenSearchFirstIndex(I firstIndex);
}
