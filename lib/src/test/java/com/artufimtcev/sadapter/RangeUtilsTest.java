package com.artufimtcev.sadapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;


public class RangeUtilsTest {

	@Test
	public void testGroupIntsToRanges() {
		nullAndEmptyList();
		singleItemResult();
		noIntsTogether();
		randomRanges();
	}


	private void noIntsTogether() {
		List<Integer> ints = Arrays.asList(100, 29, 1, 88, -2, 1266413, 125671, 23467653, 67543);
		List<Range> result = RangeUtils.groupIntsToRanges(ints);

		assertEquals(ints.size(), result.size());
	}


	private void nullAndEmptyList() {
		List<Range> ranges = RangeUtils.groupIntsToRanges(null);
		assertNull(ranges);

		ranges = RangeUtils.groupIntsToRanges(Collections.<Integer>emptyList());
		assertNull(ranges);
	}


	public void singleItemResult() {
		List<Range> ranges = RangeUtils.groupIntsToRanges(Arrays.asList(37, 35, 40, 34, 39, 36, 38));
		assertEquals(ranges.size(), 1);
		assertEquals(ranges.get(0), new Range(34, 40));
	}


	public void randomRanges() {
		List<Integer> range1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		List<Integer> range2 = Arrays.asList(10, 11, 12, 13, 14, 15);
		List<Integer> range3 = Arrays.asList(200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210);

		List<Integer> ints = new ArrayList<>();
		ints.addAll(range1);
		ints.addAll(range2);
		ints.addAll(range3);

		Collections.shuffle(ints);

		List<Range> ranges = RangeUtils.groupIntsToRanges(ints);

		assertEquals(ranges.size(), 3);

		assertRangeGeneratedCorrectly(range1, ranges.get(0));
		assertRangeGeneratedCorrectly(range2, ranges.get(1));
		assertRangeGeneratedCorrectly(range3, ranges.get(2));
	}


	private void assertRangeGeneratedCorrectly(List<Integer> rangeEntries, Range range) {
		Collections.sort(rangeEntries);
		assertEquals(range, new Range(rangeEntries.get(0), rangeEntries.get(rangeEntries.size() - 1)));
	}
}
