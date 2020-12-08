package com.polarising.spring.mockito;

import static org.junit.Assert.*;

import org.junit.Test;

import com.polarising.spring.mockito.interfaces.DataService;
import com.polarising.spring.mockito.services.SomeBusinessImpl;

public class SomeBusinessSTUBTest {

	@Test
	public void testFindTheGreatestFromAllData() {
		
		// create a new instance with the dataserviceStub
		SomeBusinessImpl businessImpl = new SomeBusinessImpl( new DataServiceStub());
		
		int result = businessImpl.findTheGreatestFromAllData();
		
		assertEquals(24, result);
		
	}

}

/*
 * Main problems of using STUBS:
 * . a lot of code to maintain and keep updated
 * . we will have to create a new stub for every scenario we want to test out
 * 
 * What is the solution to STUBS?
 * . using mocks
 * */


//creating a new Stub that is going to be used in the test case
class DataServiceStub implements DataService{

	@Override
	public int[] retrieveAllData() {
		
		return new int[] {4,6,24, 15};
	}
	
}