package com.chapter18.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoringAndRecoveringData {
	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(
			new BufferedOutputStream(
				new FileOutputStream("tempfile/Data.txt")));
		out.writeInt(-1);
		out.writeUTF("1");
		out.writeUTF("a");
		out.close();
		DataInputStream in = new DataInputStream(
			new BufferedInputStream(
				new FileInputStream("tempfile/Data.txt")));
		System.out.println(in.readInt());
		System.out.println(in.readUTF());
		System.out.println(in.readUTF());
		in.close();
	}
}
