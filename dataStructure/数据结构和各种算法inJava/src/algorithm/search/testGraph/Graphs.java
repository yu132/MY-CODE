package algorithm.search.testGraph;

import java.util.List;

import algorithm.search.interfaces.Indexable;
import algorithm.search.interfaces.NormalSearchable;
import data_structures.graphs.interfaces.IndexAllocater;
import data_structures.graphs.undirected_weighted_graph.GraphInMap;

public class Graphs<V> extends GraphInMap<Integer,Integer,V> implements NormalSearchable<Integer>,Indexable<Integer, V> {

	public Graphs() {
		super(new IntegerIndexAllocater());
	}
	
	static class IntegerIndexAllocater implements IndexAllocater<Integer>{
		private int count=0;
		
		@Override
		public Integer getNextIndex() {
			return count++;
		}
	}

	@Override
	public List<Integer> getAdjacentIndexes(Integer index) {
		return this.getAdjacentIndexes(index);
	}

	@Override
	public V getElementByIndex(Integer index) {
		return this.getAdjacentIndexAndWeight(index).getValue();
	}

}
