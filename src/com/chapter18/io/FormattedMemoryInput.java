package com.chapter18.io;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class FormattedMemoryInput {
	public static void main(String[] args) throws IOException {
		String fileName = "./src/com/chapter18/io/FormattedMemoryInput.java";
		try {
			DataInputStream in = new DataInputStream(
				new ByteArrayInputStream(
					BufferedInputFile.read(
							fileName).getBytes()));
			while(true)
				System.out.print((char)in.readByte());
		} catch (EOFException e) {
			System.out.println(e);
		}
	}
}
