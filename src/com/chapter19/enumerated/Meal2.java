package com.chapter19.enumerated;

public enum Meal2 {
	APPETIZER(Food.Appetizer.class),
	MAINCOURSE(Food.MainCourse.class),
	DESSERT(Food.Dessert.class),
	COFFEE(Food.Coffee.class);
	
	private Food[] values;
	private Meal2(Class<? extends Food> kind) {
		values = kind.getEnumConstants();
	}
	
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
	
	public Food randomSelelction() {
		return Enums.random(values);
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++) {
			for(Meal2 meal : Meal2.values()) {
				Food food = meal.randomSelelction();
				System.out.println(food);
			}
			System.out.println("-----------");
		}
	}
}
