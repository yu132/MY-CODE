package algorithm.search.util;

public class Side<SI,SW extends Comparable<SW>> implements Comparable<Side<SI,SW>>{
	private SW weight;
	private SI from;
	private SI to;
	
	public SW getWeight() {
		return weight;
	}

	public SI getFrom() {
		return from;
	}

	public SI getTo() {
		return to;
	}

	public Side(SW weight, SI from, SI to) {
		super();
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

	@Override
	public int compareTo(Side<SI, SW> o) {
		return weight.compareTo(o.weight);
	}

	@Override
	public String toString() {
		return "Side# From:"+from+", To:"+to+", Weight:"+weight;
	}

}