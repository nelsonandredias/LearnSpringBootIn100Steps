package com.polarising.spring.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.polarising.spring.mockito.interfaces.DataService;
import com.polarising.spring.mockito.services.SomeBusinessImpl;

public class SomeBusinessMOCKTest {

	@Test
	public void testFindTheGreatestFromAllData() {
		
		//create a mock for the dataService class
		DataService mockDataService = mock(DataService.class);
		
		when(mockDataService.retrieveAllData())
			.thenReturn(new int[] {4,6,24,48,52, 15});
		
	
		// create a new instance with the mock instance data
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(mockDataService);
		
		int result = businessImpl.findTheGreatestFromAllData();
		
		assertEquals(52, result);
		
	}
	
	@Test
	public void testFindTheGreatestFromAllData_ForOneValue() {
		
		//create a mock for the dataService class
		DataService mockDataService = mock(DataService.class);
		
		when(mockDataService.retrieveAllData())
			.thenReturn(new int[] {4});
		
	
		// create a new instance with the mock instance data
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(mockDataService);
		
		int result = businessImpl.findTheGreatestFromAllData();
		
		assertEquals(4, result);
		
	}

}
