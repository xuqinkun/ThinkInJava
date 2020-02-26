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

class Blip1 implements Externalizable {
	public Blip1() {
		System.out.println("Blip1 constructor");
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip1.writeExternal()");
	}

	@Override
	public void readExternal(ObjectInput in) 
			throws IOException, ClassNotFoundException {
		System.out.println("Blip1.readExternal()");
	}
	
}

class Blip2 implements Externalizable {
	public Blip2() {
		System.out.println("Blip2 constructor");
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip2.writeExternal()");
	}

	@Override
	public void readExternal(ObjectInput in) 
			throws IOException, ClassNotFoundException {
		System.out.println("Blip2.readExternal()");
	}
}

public class Blips {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		System.out.println("Constructing objects: ");
		Blip1 b1 = new Blip1();
		Blip2 b2 = new Blip2();
		String path = "tempfile/Blips.out";
		ObjectOutputStream out = new ObjectOutputStream(
			new FileOutputStream(path));
		System.out.println("Save objects:");
		out.writeObject(b1);
		out.writeObject(b2);
		out.close();
		// Now get them back:
		ObjectInputStream in = new ObjectInputStream(
			new FileInputStream(path));
		System.out.println("Recovering b1: ");
		b1 = (Blip1) in.readObject();
		System.out.println("Recovering b2: ");
		b2 = (Blip2) in.readObject();
		in.close();
		
	}
}
