package com.chapter18.io;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Blip3 implements Externalizable {
	private int i;
	private String s;
	public Blip3() {
		System.out.println("Blip3 Constructor");
	}
	
	public Blip3(int i, String s) {
		System.out.println("Blip3(int i, String s)");
		this.i = i;
		this.s = s;
	}

	@Override
	public String toString() {
		return s + i;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) 
			throws IOException {
		System.out.println("Blip3.writeExternal()");
		out.writeObject(s);
		out.writeInt(i);
	}

	@Override
	public void readExternal(ObjectInput in) 
			throws IOException, ClassNotFoundException {
		System.out.println("Blip3.readExternal()");
		// You must to do this
		s = (String) in.readObject();
		i = in.readInt();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Constructing objects:");
		Blip3 b3 = new Blip3(47, "A String ");
		System.out.println(b3);
		String path = "tempfile/Blip3.out";
		ObjectOutputStream out = new ObjectOutputStream(
			new FileOutputStream(path));
		System.out.println("Saving object:");
		out.writeObject(b3);
		out.close();
		// Now get it back:
		ObjectInputStream in = new ObjectInputStream(
			new FileInputStream(path));
		System.out.println("Recovering b3:");
		b3 = (Blip3) in.readObject();
		in.close();
		System.out.println(b3);
	}
}
