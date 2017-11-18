package data_structures.graphs.interfaces;

import java.util.List;
import java.util.Map;

public interface WeightedGraph<I,W extends Comparable<W>,V> {

	public I addVertex(V value,Map<I,W> weightedMap);
	
	public List<I> getIndexes(V value);
	
	public boolean deleteVertex(I index);
	
	public ValueAndAdjacencyMap<V,W,I> getAdjacentIndexAndWeight(I index);
	
	public void clear();
	
	class ValueAndAdjacencyMap<SV,SW,SI>{
		Map<SI,SW> adjacencyList;
		SV value;
		public ValueAndAdjacencyMap(SV value,Map<SI, SW> adjacencyList) {
			super();
			this.adjacencyList = adjacencyList;
			this.value = value;
		}
		public Map<SI, SW> getAdjacencyMap() {
			return adjacencyList;
		}
		public SV getValue() {
			return value;
		}
	}
}