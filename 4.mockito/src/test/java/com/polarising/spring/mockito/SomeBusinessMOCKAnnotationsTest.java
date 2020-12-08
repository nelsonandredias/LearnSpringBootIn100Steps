package com.polarising.spring.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.polarising.spring.mockito.interfaces.DataService;
import com.polarising.spring.mockito.services.SomeBusinessImpl;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMOCKAnnotationsTest {

	//create a mock for the dataService class
	@Mock
	DataService mockDataService;
	
	//inject the mock instance in the class instance we want to test out
	@InjectMocks
	SomeBusinessImpl businessImpl;
	
	@Test
	public void testFindTheGreatestFromAllData() {

		when(mockDataService.retrieveAllData())
			.thenReturn(new int[] {4,6,24,48,52, 15});
				
		assertEquals(52, businessImpl.findTheGreatestFromAllData());
		
	}
	
	@Test
	public void testFindTheGreatestFromAllData_ForOneValue() {

		when(mockDataService.retrieveAllData())
			.thenReturn(new int[] {4});

		assertEquals(4, businessImpl.findTheGreatestFromAllData());
		
	}
	
	@Test
	public void testFindTheGreatestFromAllData_NoVallues() {

		when(mockDataService.retrieveAllData())
			.thenReturn(new int[] {});

		assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
		
	}

}
