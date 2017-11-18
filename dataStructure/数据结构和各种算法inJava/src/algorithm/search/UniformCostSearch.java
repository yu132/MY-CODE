package algorithm.search;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import algorithm.search.interfaces.Indexable;
import algorithm.search.interfaces.Non_informationSearcher;
import algorithm.search.interfaces.SearchAim;
import algorithm.search.interfaces.WeightedSearchDetector;
import algorithm.search.interfaces.WeightedSearchable;
import algorithm.search.util.Side;
import data_structures.list.SortedArrayList;
import data_structures.list.interfaces.SortedList;

public class UniformCostSearch<T extends WeightedSearchable<I, W> & Indexable<I, RT>, RT, I, W extends Comparable<W>>
		implements Non_informationSearcher<T, RT, I> {

	private WeightedSearchDetector<I,W> searchDetector;
	
	@Override
	public I search(I startIndex, T container, SearchAim<I> aim) {
		SortedList<Side<I,W>> frontier=new SortedArrayList<>();
		Set<I> hasSearchIndexSet=new TreeSet<>();
		if(searchDetector!=null)
			searchDetector.doWhenSearchFirstIndex(startIndex);
		List<Side<I,W>> firstlist=container.getVertexSide(startIndex);
		if(searchDetector!=null)
			searchDetector.doWhenGetNextIndex(firstlist);
		put(frontier,firstlist);
		hasSearchIndexSet.add(startIndex);
		while(!frontier.isEmpty()){
			Side<I,W> cheapSide=frontier.getFirst();
			frontier.removeFirst();
			if(hasSearchIndexSet.contains(cheapSide.getTo()))
				continue;
			else
				hasSearchIndexSet.add(cheapSide.getTo());
			if(searchDetector!=null)
				searchDetector.doWhenSearchthisIndex(cheapSide);
			if(aim.isAim(cheapSide.getTo()))
				return cheapSide.getTo();
			else{
				List<Side<I,W>> l=container.getVertexSide(cheapSide.getTo());
				if(searchDetector!=null)
					searchDetector.doWhenGetNextIndex(l);
				put(frontier,l);
			}
		}
		return null;
	}
	
	public WeightedSearchDetector<I, W> getSearchDetector() {
		return searchDetector;
	}

	public void setSearchDetector(WeightedSearchDetector<I, W> searchDetector) {
		this.searchDetector = searchDetector;
	}

	public UniformCostSearch() {}
	
	public UniformCostSearch(WeightedSearchDetector<I, W> searchDetector) {
		super();
		this.searchDetector = searchDetector;
	}

	private void put(SortedList<Side<I,W>> sl,List<Side<I,W>> l){
		for(Side<I,W> s:l)
			sl.add(s);
	}
	
}
