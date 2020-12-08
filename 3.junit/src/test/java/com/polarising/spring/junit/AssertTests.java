package com.polarising.spring.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AssertTests {

	@Test
	void assertEqualsTest() {
		
		assertEquals(1, 1);
		
	}

	@Test
	void assertTrueTest() {
		
		assertTrue( 2 > 1);
		
	}

	@Test
	void assertArrayEqualsTest() {
		
		assertArrayEquals(new int[] {1,2,3}, new int[] {1,2,3});
		
	}
	
}
