package com.artufimtcev.sadapter;

class Range {

	final int from;
	final int to;


	public Range(int from, int to) {
		if (from > to) {
			throw new IllegalArgumentException("Unable to create a range: from is bigger than to. from = " + from + "; to = " + to);
		}

		this.from = from;
		this.to = to;
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
}
