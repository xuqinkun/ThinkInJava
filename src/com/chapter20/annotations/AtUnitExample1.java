package com.chapter20.annotations;


public class AtUnitExample1 {
	public String methodOne() {
		return "This is methodOne";
	}
	
	public int methodTwo() {
		System.out.println("This is methodOne");
		return 2;
	}
	@Test boolean methodOneTest() {
		return methodOne().equals("This is methodOne");
	}
	
}
