package com.chapter18.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {
	public static String read(String filename) throws IOException {
		BufferedReader br = 
				new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while((s = br.readLine()) != null)
			sb.append(s + "\n");
		br.close();
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(read("./src/com/chapter18/io/BufferedInputFile.java"));
	}
}
