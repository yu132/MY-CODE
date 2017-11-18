package algorithm.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import algorithm.search.interfaces.Indexable;
import algorithm.search.interfaces.Non_informationSearcher;
import algorithm.search.interfaces.SearchAim;
import algorithm.search.interfaces.SearchDetector;
import algorithm.search.interfaces.NormalSearchable;

public class BFS <T extends NormalSearchable<I>&Indexable<I,RT>, RT, I> implements Non_informationSearcher<T, RT, I>{

	private SearchDetector<I> searchDetector;
	
	
	
	
	@Override
	public I search(I startIndex, T container, SearchAim<I> aim) {
		Queue<I> queue=new LinkedList<>();
		Set<I> hasSearchIndexSet=new TreeSet<>();
		queue.offer(startIndex);
		while(!queue.isEmpty()){
			I index=queue.poll();
			if(hasSearchIndexSet.contains(index))
				continue;
			else
				hasSearchIndexSet.add(index);
			if(searchDetector!=null)
				searchDetector.doWhenSearchthisIndex(index);
			if(aim.isAim(index))
				return index;
			else{
				List<I> list=container.getAdjacentIndexes(index);
				if(searchDetector!=null)
					searchDetector.doWhenGetnextIndex(list);
				for(I i:list)
					queue.offer(i);
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

	public BFS() {}

	public BFS(SearchDetector<I> searchDetector) {
		this.searchDetector = searchDetector;
	}
	
}
