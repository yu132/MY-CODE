package algorithm.search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import algorithm.search.interfaces.Indexable;
import algorithm.search.interfaces.Non_informationSearcher;
import algorithm.search.interfaces.SearchAim;
import algorithm.search.interfaces.SearchDetector;
import algorithm.search.util.Counter;
import algorithm.search.interfaces.NormalSearchable;

public class DepthLimitedSearch<T extends NormalSearchable<I>&Indexable<I,RT>, RT, I> implements Non_informationSearcher<T, RT, I>{

	private SearchDetector<I> searchDetector;
	
	private int maxLayer;
	
	@Override
	public I search(I startIndex, T container, SearchAim<I> aim) {
		Deque<I> stack = new ArrayDeque<>();
		Deque<Counter> layerStack = new ArrayDeque<>();
		int layer=0;
		Counter nowCounter=new Counter(1);
		Set<I> hasSearchIndexSet=new TreeSet<>();
		stack.push(startIndex);
		while(!stack.isEmpty()){
			I index=stack.pop();
			while(nowCounter.isZero()){
				nowCounter=layerStack.pop();
				layer--;
			}
			nowCounter.countDown();
			if(hasSearchIndexSet.contains(index))
				continue;
			else
				hasSearchIndexSet.add(index);
			if(searchDetector!=null)
				searchDetector.doWhenSearchthisIndex(index);
			if(aim.isAim(index))
				return index;
			else{
				if(layer==maxLayer){
					continue;
				}
				List<I> list=container.getAdjacentIndexes(index);
				if(searchDetector!=null)
					searchDetector.doWhenGetnextIndex(list);
				if(list.size()>0){
					layerStack.push(nowCounter);
					layer++;
					nowCounter=new Counter(list.size());
				}
				for(int i=list.size()-1;i>=0;i--)
					stack.push(list.get(i));
			}
		}
		return null;
	}
	
	public SearchDetector<I> getSearchDetector() {
		return searchDetector;
	}

	public void setSearchDetector(SearchDetector<I> searchDetector) {
		this.searchDetector = searchDetector;
	}

	public int getMaxLayer() {
		return maxLayer;
	}

	public void setMaxLayer(int maxLayer) {
		this.maxLayer = maxLayer;
	}

	public DepthLimitedSearch(int maxLayer) {
		super();
		this.maxLayer = maxLayer;
	}

	public DepthLimitedSearch(SearchDetector<I> searchDetector, int maxLayer) {
		super();
		this.searchDetector = searchDetector;
		this.maxLayer = maxLayer;
	}

}
