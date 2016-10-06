package com.artufimtcev.sadapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


final class RangeUtils {

	private RangeUtils() {}


	static List<Range> groupIntsToRanges(List<Integer> integers) {
		if (integers == null || integers.isEmpty()) {
			return null;
		}

		List<Range> result = new ArrayList<>();
		Collections.sort(integers);

		Integer rangeStart = null;

		for (int i = 0; i < integers.size(); i++) {
			if (rangeStart == null) {
				rangeStart = integers.get(i);
			} else if (integers.get(i) - integers.get(i - 1) != 1) {
				result.add(new Range(rangeStart, integers.get(i - 1)));
				rangeStart = integers.get(i);
			}

			if (integers.size() - i == 1) {
				result.add(new Range(rangeStart, integers.get(i)));
			}
		}

		return result;
	}


	static Set<Range> findConjuctions(List<Range> first, List<Range> second) {
		Set<Range> result = new HashSet<>();
		for (Range lhs : first) {
			result.addAll(findConjuctions(second, lhs));
		}
		return result;
	}


	static Set<Range> findConjuctions(List<Range> list, Range rhs) {
		Set<Range> result = new HashSet<>();
		for (Range lhs : list) {
			Range range = lhs.and(rhs);
			if (range != null) {
				result.add(range);
			}
		}
		return result;
	}


	static boolean rangesContain(Collection<Range> ranges, int number) {
		for (Range range : ranges) {
			if (range.contains(number)) {
				return true;
			}
		}

		return false;
	}
}
