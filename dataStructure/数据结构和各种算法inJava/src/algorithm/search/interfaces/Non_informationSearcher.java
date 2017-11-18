package algorithm.search.interfaces;

public interface Non_informationSearcher<T extends Searchable<I>&Indexable<I,RT>, RT, I> {

	public I search(I startIndex,T container,SearchAim<I> aim);
	
}
