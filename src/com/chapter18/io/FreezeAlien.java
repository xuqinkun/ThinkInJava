package com.chapter18.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
class Alien implements Serializable {}

public class FreezeAlien {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(
			new FileOutputStream("tempfile/x.file"));
		Alien alien = new Alien();
		out.writeObject(alien);
		out.close();
	}
}
