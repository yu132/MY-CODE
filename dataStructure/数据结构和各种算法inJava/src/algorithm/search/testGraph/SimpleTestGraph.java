package algorithm.search.testGraph;

import java.util.ArrayList;
import java.util.List;

import algorithm.search.IterativeDeepeningSearch;
import algorithm.search.interfaces.Indexable;
import algorithm.search.interfaces.NormalSearchable;
import algorithm.search.interfaces.SearchAim;
import algorithm.search.interfaces.SearchDetector;
import algorithm.search.interfaces.WeightedSearchDetector;
import algorithm.search.interfaces.WeightedSearchable;
import algorithm.search.util.Side;

public class SimpleTestGraph implements Indexable<Integer, Character>,WeightedSearchable<Integer,Integer>,NormalSearchable<Integer>{

	private int[][] test={ {0,4,100,1,0,0,0,0,0,0},
						   {0,0,0,0,1,0,0,0,0,0},
						   {0,0,0,0,0,50,1,0,0,0},
						   {0,0,0,0,0,0,0,0,0,0},
						   {0,0,0,0,0,0,0,0,0,0},
						   {0,0,0,0,0,0,0,0,0,0},
						   {0,0,0,0,0,0,0,1,1,0},
						   {0,0,0,0,0,0,0,0,0,0},
						   {0,0,0,0,0,0,0,0,0,1},
						   {0,0,0,0,0,0,0,0,0,0}, };
	
	private char[] value ={'A','B','C','D','E','F','G','H','I','J',};
	
	public SearchAim<Integer> aim=new ExampleAim();
	
	public ExampleSearchDetector esd=new ExampleSearchDetector();
	
	public ExampleWeightedSearchDetector ewsd=new ExampleWeightedSearchDetector();


	@Override
	public List<Integer> getAdjacentIndexes(Integer index) {
		List<Integer> list=new ArrayList<>();
		for(int i=0;i<10;i++){
			if(test[index][i]>0)
				list.add(i);
		}
		return list;
	}

	@Override
	public List<Side<Integer, Integer>> getVertexSide(Integer fromIndex) {
		List<Side<Integer, Integer>> list=new ArrayList<>();
		for(int i=0;i<10;i++){
			if(test[fromIndex][i]>0)
				list.add(new Side<Integer, Integer>(test[fromIndex][i],fromIndex, i));
		}
		return list;
	}

	@Override
	public Character getElementByIndex(Integer index) {
		return value[index];
	}

	class ExampleAim implements SearchAim<Integer>{

		@Override
		public boolean isAim(Integer index) {
			if(getElementByIndex(index)=='I')
				return true;
			else
				return false;
		}
	}
	
	class ExampleSearchDetector implements SearchDetector<Integer>{

		@Override
		public void doWhenSearchthisIndex(Integer i) {
			System.out.println("vertex:"+i);
		}

		@Override
		public void doWhenGetnextIndex(List<Integer> indexes) {
			System.out.println("nextVertex:"+indexes);
		}
	}
	
	class ExampleWeightedSearchDetector implements WeightedSearchDetector<Integer,Integer>{

		@Override
		public void doWhenSearchthisIndex(Side<Integer, Integer> side) {
			System.out.println(side);
		}

		@Override
		public void doWhenGetNextIndex(List<Side<Integer, Integer>> sides) {
			System.out.println("sides:"+sides);
		}

		@Override
		public void doWhenSearchFirstIndex(Integer firstIndex) {
			System.out.println("firstIndex:"+firstIndex);
		}
		
	}
	
	public static void main(String[] args) {
		SimpleTestGraph stg=new SimpleTestGraph();
		//BFS<SimpleTestGraph,Character,Integer> searcher=new BFS<>(stg.esd);
		//DFS<SimpleTestGraph,Character,Integer> searcher=new DFS<>(stg.esd);
		//UniformCostSearch<SimpleTestGraph,Character,Integer,Integer> searcher=new UniformCostSearch<>(stg.ewsd);
		//DepthLimitedSearch<SimpleTestGraph,Character,Integer> searcher=new DepthLimitedSearch<>(stg.esd,3);
		IterativeDeepeningSearch<SimpleTestGraph,Character,Integer> searcher=new IterativeDeepeningSearch<>(stg.esd,2);
		System.out.println(searcher.search(0, stg, stg.aim));
	}

}
