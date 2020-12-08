package com.polarising.spring.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import com.polarising.spring.junit.operations.MyMath;

class MyMathTest {

	MyMath myMath = new MyMath();
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("Before all");
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("After all");
	}
	
	@BeforeEach
	public void beforeTestCase() {
		System.out.println("Before testCase");
	}
	
	@AfterEach
	public void afterTestCase() {
		System.out.println("After testCase");
	}
	
	@Test
	public void sum_with3Numbers() {
		System.out.println("sum_with3Numbers()");

		assertEquals(6, myMath.sum(new int[] {1,2,3}));
		
		System.out.println("result ------------> " + myMath.sum(new int[] {1,2,3}));
		
	}

	@Test
	public void sum_with1Numbers() {
		System.out.println("sum_with1Numbers()");
		
		assertEquals(3, myMath.sum(new int[] {3}));
		
		System.out.println("result ------------> " + myMath.sum(new int[] {3}));
		
	}
}
