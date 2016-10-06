package com.artufimtcev.sadapter;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;


public class RangeTest {

	@Test
	public void testContains() {
		testTrivialContains();
		testBorderContains();
	}


	@Test
	public void testCrossesWith() {
		testTrivialCrossesWith();
	}


	@Test
	public void testAnd() {
		testTrivialAnd();

	}


	private void testTrivialContains() {
		Range targetRange = new Range(500, 600);

		Range nestedRange = new Range(510, 590);
		Range partlyNestedRangeStart = new Range(400, 550);
		Range partlyNestedRangeEnd = new Range(550, 650);
		Range notCrossingRange = new Range(1, 2);

		assertTrue(targetRange.contains(nestedRange));
		assertFalse(targetRange.contains(partlyNestedRangeStart));
		assertFalse(targetRange.contains(partlyNestedRangeEnd));
		assertFalse(nestedRange.contains(notCrossingRange));
	}


	private void testBorderContains() {
		Range targetRange = new Range(100, 200);

		Range equalRange = new Range(100, 200);
		Range almostEqualRangeStart = new Range(99, 200);
		Range almostEqualRangeEnd = new Range(100, 201);

		assertTrue(targetRange.contains(equalRange));
		assertTrue(equalRange.contains(targetRange));

		assertFalse(targetRange.contains(almostEqualRangeStart));
		assertFalse(targetRange.contains(almostEqualRangeEnd));

		almostEqualRangeStart = new Range(101, 200);
		almostEqualRangeEnd = new Range(100, 199);

		assertTrue(targetRange.contains(almostEqualRangeStart));
		assertTrue(targetRange.contains(almostEqualRangeEnd));
	}


	private void testTrivialCrossesWith() {
		Range targetRange = new Range(400, 500);

		Range equalRange = new Range(400, 500);
		Range partlyCrossingRangeStart = new Range(350, 450);
		Range partlyCrossingRangeEnd = new Range(450, 550);
		Range notCrossingRangeBefore = new Range(1, 3);
		Range notCrossingRangeAfter = new Range(1001, 1003);

		assertCross(targetRange, equalRange);
		assertCross(targetRange, partlyCrossingRangeStart);
		assertCross(targetRange, partlyCrossingRangeEnd);

		assertDontCross(targetRange, notCrossingRangeBefore);
		assertDontCross(targetRange, notCrossingRangeAfter);
	}


	private void testTrivialAnd() {
		Range targetRange = new Range(500, 600);

		Range equalRange = new Range(500, 600);
		Range nestedRange = new Range(510, 590);
		Range partlyNestedRangeStart = new Range(400, 550);
		Range partlyNestedRangeEnd = new Range(550, 650);
		Range notCrossingRange = new Range(1, 2);

		assertEquals(targetRange.and(equalRange), targetRange);
		assertEquals(targetRange.and(nestedRange), nestedRange);
		assertEquals(targetRange.and(partlyNestedRangeStart), new Range(targetRange.from, partlyNestedRangeStart.to));
		assertEquals(targetRange.and(partlyNestedRangeEnd), new Range(partlyNestedRangeEnd.from, targetRange.to));
		assertNull(targetRange.and(notCrossingRange));
	}


	private void assertCross(Range lhs, Range rhs) {
		assertTrue(lhs.crossesWith(rhs));
		assertTrue(rhs.crossesWith(lhs));
	}


	private void assertDontCross(Range lhs, Range rhs) {
		assertFalse(lhs.crossesWith(rhs));
		assertFalse(rhs.crossesWith(lhs));
	}
}
