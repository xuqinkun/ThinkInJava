package com.chapter18.io;

import java.io.PrintWriter;

public class ChangeSystemOut {
	public static void main(String[] args) {
		// The second arguments means auto-flush the buffer
		PrintWriter out = new PrintWriter(System.out, true);
		out.println("Hello World!");
		//out.flush();
		//out.close();
	}
}
