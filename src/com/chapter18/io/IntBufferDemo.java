package com.chapter18.io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
	private static final int SIZE = 1024;
	
	public static void main(String[] args) {
		ByteBuffer buff = ByteBuffer.allocate(SIZE);
		IntBuffer ib = buff.asIntBuffer();
		ib.put(new int[]{1,2,3,4,5,6,11});
		System.out.println(ib.get(4));
		ib.put(3, 86);
		ib.flip();
		while(ib.hasRemaining()) {
			int i = ib.get();
			System.out.print(i + " ");
		}
	}
}
