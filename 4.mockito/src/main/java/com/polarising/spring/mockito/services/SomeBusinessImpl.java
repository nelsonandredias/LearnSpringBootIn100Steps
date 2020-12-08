package com.polarising.spring.mockito.services;

import com.polarising.spring.mockito.interfaces.DataService;

public class SomeBusinessImpl {

	private DataService dataService;
	
	//constructor
	public SomeBusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int findTheGreatestFromAllData() {
		
		int[] data = dataService.retrieveAllData();
		
		int greatest = Integer.MIN_VALUE;
		
		for (int value : data) {
			
			if(value > greatest) {
				greatest = value;
			}
		}
		return greatest;
	}
	
}
