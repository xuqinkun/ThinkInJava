package com.chapter18.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

@SuppressWarnings("serial")
class Data implements Serializable {
	private int n;
	public Data(int n) { this.n = n; }
	public String toString() {
		return Integer.toString(n);
	}
}

@SuppressWarnings("serial")
public class Worm implements Serializable {
	private static Random rand = new Random(55);
	private Data[] d = {
		new Data(rand.nextInt(10)),
		new Data(rand.nextInt(10)),
		new Data(rand.nextInt(10))
	};
	private Worm next;
	private char c;
	// Value of i == number of segments
	public Worm(int i, char x) {
		System.out.println("Worm constructor: " + i);
		c = x;
		if(--i > 0)
			next = new Worm(i, (char)(x + 1));
	}
	
	public Worm() {
		System.out.println("Default constructor");
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for(Data dat : d) 
			result.append(dat);
		result.append(")");
		if(next != null)
			result.append(next);
		return result.toString();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Worm w = new Worm(6, 'a');
		System.out.println("w = " + w);
		String path = "tempfile/worm.out";
		ObjectOutputStream out = 
			new ObjectOutputStream(
				new FileOutputStream(path));
		out.writeObject("Worm storage\n");
		out.writeObject(w);
		out.close();
		
		ObjectInputStream in = 
			new ObjectInputStream(
				new FileInputStream(path));
		String s = (String) in.readObject();
		Worm w2 = (Worm) in.readObject();
		
		in.close();
		System.out.println(s + "w2 = " + w);
		
		ByteArrayOutputStream bout = 
				new ByteArrayOutputStream();
		ObjectOutputStream out2 = new ObjectOutputStream(bout);
		out2.writeObject("Worm storage\n");
		out2.writeObject(w);
		out2.flush();
		
		ObjectInputStream in2 = new ObjectInputStream(
			new ByteArrayInputStream(bout.toByteArray()));
		s = (String) in2.readObject();
		Worm w3 = (Worm) in2.readObject();
		System.out.println(s + "w3 = " + w3);
	}
}
