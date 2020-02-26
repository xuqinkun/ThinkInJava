package com.chapter19.enumerated;

public interface Food {
	enum Appetizer implements Food {
		SALAD, SOUP, SPRING_ROLLS;
	}
	enum MainCourse implements Food {
		LASAGNE, BURRITO, PAD_THAT;
	}
	
	enum Dessert implements Food {
		TURAMISU, GELATO, BLACK_FOREST_CAKE;
	}
	enum Coffee implements Food {
		BLACK_COFFEE, DECAF_COFFEE, ESPRESSO;
	}
}

