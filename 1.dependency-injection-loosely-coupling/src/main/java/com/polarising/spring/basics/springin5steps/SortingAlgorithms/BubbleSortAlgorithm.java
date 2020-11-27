package com.polarising.spring.basics.springin5steps.SortingAlgorithms;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.polarising.spring.basics.springin5steps.SortingAlgorithms.Interfaces.SortAlgorithm;

//@Component annotation tells spring that an instance of this class can be considered as a bean
@Component
@Primary
public class BubbleSortAlgorithm implements SortAlgorithm{

	// implementing bubble sort array
	public int[] sort(int[] numbers) {
		
		//1. Implementing Logic for Bubble Sort
		
		//2.Return the result
		return numbers;
	}
	
}
