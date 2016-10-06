package com.artufimtcev.sadapter;

class Range implements Cloneable {

	final int from;
	final int to;


	public Range(int from, int to) {
		if (from > to) {
			throw new IllegalArgumentException("Unable to create a range: from is bigger than to. from = " + from + "; to = " + to);
		}

		this.from = from;
		this.to = to;
	}


	public Range and(Range range) {
		if (!crossesWith(range)) return null;

		if (this.contains(range)) return range.clone();

		if (range.contains(this)) return this.clone();

		if (this.from > range.from) return new Range(from, range.to);

		if (this.to < range.to) return new Range(range.from, to);

		throw new RuntimeException("Something is completely wrong, this situation is not covered: " + this.toString() + " and " + range.to);
	}


	public boolean contains(Range range) {
		return this.from <= range.from && this.to >= range.to;
	}


	public boolean contains(int i) {
		return i >= from && i <= to;
	}


	public boolean crossesWith(Range range) {
		return this.contains(range.from) || this.contains(range.to) || range.contains(from) || range.contains(to);
	}


	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Range range = (Range) o;

		if(from != range.from) return false;
		return to == range.to;

	}


	@Override
	public int hashCode() {
		int result = from;
		result = 31 * result + to;
		return result;
	}


	@Override
	public String toString() {
		return "Range{" +
				"from=" + from +
				", to=" + to +
				'}';
	}


	@Override
	public Range clone() {
		return new Range(from, to);
	}
}
