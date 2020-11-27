package com.polarising.spring.basics.springin5steps.SortingAlgorithms.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.polarising.spring.basics.springin5steps.SortingAlgorithms.BubbleSortAlgorithm;
import com.polarising.spring.basics.springin5steps.SortingAlgorithms.Interfaces.SortAlgorithm;

// @Component annotation tells spring that an instance of this class can be considered as a bean
@Component
public class BinarySearchImpl {
	
	//@Autowired annotation tells spring that this is a dependency of a bean "BinarySearchImpl"
	@Autowired
	private SortAlgorithm sortAlgorithm;
	
	
	public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
		super();
		this.sortAlgorithm = sortAlgorithm;
	}


	public int binarySearch(int[] numbers, int numberToSearchFor) {
		
		
		//sorting numbers using a sort algorithm
		int[] sortedNumbers = sortAlgorithm.sort(numbers);
		
		System.out.println("Algorithm being used -----------> " + sortAlgorithm);
		
		//2.Search the array
		
		//3.Return the result
		return 3;
	}
	
	

}
