package com.chapter18.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

public class BasicFileOutput {
	static String file = "tempfile/BasicFileOutput.out";
	static String filename = "./src/com/chapter18/io/BasicFileOutput.java";
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(
			new StringReader(
				BufferedInputFile.read(filename)));
		PrintWriter out = new PrintWriter(
			new BufferedWriter(new FileWriter(file)));
		int lineCount = 1;
		String s;
		while((s = in.readLine()) != null)
			out.println(lineCount++ + ": " + s);
		// 必须显示调用close(),否则缓冲区内容不会被刷新清空
		out.close();
		System.out.println(BufferedInputFile.read(file));
	}
}
