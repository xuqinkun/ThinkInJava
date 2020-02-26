package com.chapter19.enumerated;

enum Activity { SITTING, LYING, STRANDING, HOPPING,
	RUNNING, DODGING, JUMPINS, FALLING, FLYING }

public class RandomTest {
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++)
			System.out.print(Enums.random(Activity.class) + " ");
	}
}
