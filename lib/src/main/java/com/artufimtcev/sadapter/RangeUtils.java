package com.artufimtcev.sadapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
}
