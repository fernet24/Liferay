package com.technical;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoundTest {

	@Test
	void test() 
	{
		double value = 3.8999;
		Round.nearestOnes(value);
		System.out.println(value);
		
		Round.nearestFifth(value);
		System.out.println(value);
	}

}
