package com.chapter19.enumerated;

import java.util.Random;

public class Enums {
	private static Random rand = new Random(66);
	
	public static <T extends Enum<T>> T random(Class<T> ec) {
		return random(ec.getEnumConstants());
	}
	
	public static <T> T random(T[] values) {
		return values[rand.nextInt(values.length)];
	}
}
