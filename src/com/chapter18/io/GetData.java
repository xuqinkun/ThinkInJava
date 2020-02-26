package com.chapter18.io;

import java.nio.ByteBuffer;

public class GetData {
	private static final int SIZE = 1024;
	public static void main(String[] args) {
		ByteBuffer buff = ByteBuffer.allocate(SIZE);
		// Allocation automatically zeroes the ByteBuffer
		int i = 0;
		while(i++ < buff.limit()) 
			if(buff.get() != 0)
				System.out.println("nonzero");
		System.out.println("i = " + i);
		buff.rewind();
		// Store and read a char array:
		buff.asCharBuffer().put("Howdy");
		char c;
		while((c = buff.getChar()) != 0)
			System.out.print(c + " ");
		System.out.println();
		buff.rewind();
		
		// Store and read a short:
		buff.asShortBuffer().put((short)10001);
		System.out.println(buff.getShort());
		buff.rewind();
		
		buff.asIntBuffer().put(99471142);
		System.out.println(buff.getInt());
		buff.rewind();
		
		buff.asLongBuffer().put(99471142);
		System.out.println(buff.getLong());
		buff.rewind();
		
		buff.asFloatBuffer().put(99471142);
		System.out.println(buff.getFloat());
		buff.rewind();
		
		buff.asDoubleBuffer().put(99471142);
		System.out.println(buff.getDouble());
		buff.rewind();
	}
}
