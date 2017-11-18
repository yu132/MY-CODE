package data_structures.graphs.interfaces;

import java.util.List;

public interface UnweightedGraph<V,I> {

	public I addVertex(V value,List<I> unweightedList);
	
	public List<I> getIndexes(V value);
	
	public boolean deleteVertex(I index);
	
	public List<I> getAdjacentIndex(I index);
	
	
}
