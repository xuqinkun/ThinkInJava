package com.chapter18.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ThawAlien {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(
			new FileInputStream(new File("tempfile/x.file")));
		Object obj = in.readObject();
		System.out.println(obj.getClass());
		in.close();
	}
}
