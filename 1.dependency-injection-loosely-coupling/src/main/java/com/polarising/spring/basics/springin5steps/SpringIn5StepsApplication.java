package com.polarising.spring.basics.springin5steps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.polarising.spring.basics.springin5steps.SortingAlgorithms.BubbleSortAlgorithm;
import com.polarising.spring.basics.springin5steps.SortingAlgorithms.QuickSortAlgorithm;
import com.polarising.spring.basics.springin5steps.SortingAlgorithms.Implementations.BinarySearchImpl;

@SpringBootApplication
public class SpringIn5StepsApplication {

	public static void main(String[] args) {
		
		//create an instance
		//BinarySearchImpl binarySearchImpl = new BinarySearchImpl(new QuickSortAlgorithm());
		
		//int result = binarySearchImpl.binarySearch(new int[] {12, 10, 8, 6, 4, 2}, 3);
		
		//System.out.println("result is ------------------------> "+ result);
		
		//-------------------------------------------------------------
		
		//since the beans will be created and managed in the spring application context, we can use it
		// the context to call and use the beans
		
		ApplicationContext appContext = SpringApplication.run(SpringIn5StepsApplication.class, args);

		BinarySearchImpl binarySearchImpl = appContext.getBean(BinarySearchImpl.class);
		
		int result = binarySearchImpl.binarySearch(new int[] {12, 10, 8, 6, 4, 2}, 3);
		System.out.println("result is ------------------------> "+ result);
	}

}
