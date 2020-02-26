package com.chapter19.enumerated;

import java.util.Random;

import util.Generator;

enum CartoonCharacter
implements Generator<CartoonCharacter> {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

	private Random rand = new Random(66); 
	@Override
	public CartoonCharacter next() {
		return values()[rand.nextInt(values().length)];
	}
}

public class EnumImplementation {
	public static <T> void printNext(Generator<T> gen) {
		System.out.print(gen.next() + ", ");
	}
	
	public static void main(String[] args) {
		CartoonCharacter cc = CartoonCharacter.BOB;
		for(int i = 0; i < 10; i++)
			printNext(cc);
	}
}
