package data_structures.graphs.undirected_weighted_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data_structures.graphs.interfaces.IndexAllocater;
import data_structures.graphs.interfaces.WeightedGraph;

public class GraphInMap<I,W extends Comparable<W>,V> implements WeightedGraph<I, W, V> {//W是权重类型

	private Map<I,ValueAndAdjacencyMap<V,W,I>> adjacencyMatrix;
	
	private IndexAllocater<I> indexAllocater; 

	public GraphInMap(IndexAllocater<I> indexAllocater) {
		adjacencyMatrix=new HashMap<>();
		this.indexAllocater = indexAllocater;
	}

	@Override
	public I addVertex(V value, Map<I, W> weightedMap) {
		I index=indexAllocater.getNextIndex();
		adjacencyMatrix.put(index, new ValueAndAdjacencyMap<V,W,I>(value,weightedMap));
		return index;
	}
	
	@Override
	public List<I> getIndexes(V value) {
		List<I> list=new ArrayList<>();
		for(Map.Entry<I,ValueAndAdjacencyMap<V,W,I>> entry : adjacencyMatrix.entrySet()){
			if(entry.getValue().getValue().equals(value))
				list.add(entry.getKey());
		}
		return list;
	}
	
	@Override
	public boolean deleteVertex(I index) {
		if(adjacencyMatrix.remove(index) != null){
			for(WeightedGraph.ValueAndAdjacencyMap<V,W,I> v:adjacencyMatrix.values()){
				v.getAdjacencyMap().remove(index);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public ValueAndAdjacencyMap<V, W, I> getAdjacentIndexAndWeight(I index) {
		return adjacencyMatrix.get(index);
	}

	@Override
	public void clear() {
		adjacencyMatrix.clear();
	}
	
}
