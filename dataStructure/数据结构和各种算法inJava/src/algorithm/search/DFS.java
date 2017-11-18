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
import algorithm.search.interfaces.NormalSearchable;

public class DFS <T extends NormalSearchable<I>&Indexable<I,RT>, RT, I> implements Non_informationSearcher<T, RT, I>{

	private SearchDetector<I> searchDetector;
	
	@Override
	public I search(I startIndex, T container, SearchAim<I> aim) {
		Deque<I> stack = new ArrayDeque<>();
		Set<I> hasSearchIndexSet=new TreeSet<>();
		stack.push(startIndex);
		while(!stack.isEmpty()){
			I index=stack.pop();
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
				for(int i=list.size()-1;i>=0;i--)
					stack.push(list.get(i));
			}
		}
		return null;
	}

	public DFS() {}

	public DFS(SearchDetector<I> searchDetector) {
		this.searchDetector = searchDetector;
	}

	public SearchDetector<I> getSearchDetector() {
		return searchDetector;
	}

	public void setSearchDetector(SearchDetector<I> searchDetector) {
		this.searchDetector = searchDetector;
	}
	
}
