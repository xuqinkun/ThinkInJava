package com.chapter18.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestEOF {
	public static void main(String[] args) throws IOException {
		String fileName = "./src/com/chapter18/io/TestEOF.java";
		DataInputStream in = new DataInputStream(
			new BufferedInputStream(
				new FileInputStream(fileName)));
		while(in.available() != 0)
			System.out.print((char)in.readByte());
		in.close();
	}
}
