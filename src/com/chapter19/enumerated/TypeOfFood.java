package com.chapter19.enumerated;

import static com.chapter19.enumerated.Food.*;

public class TypeOfFood {
	public static void main(String[] args) {
		Food food = Food.Appetizer.SALAD;
		food = MainCourse.LASAGNE;
		food = Dessert.GELATO;
		food = Coffee.BLACK_COFFEE;
	}
}
