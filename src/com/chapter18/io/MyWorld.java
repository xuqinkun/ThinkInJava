package com.chapter18.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
class House implements Serializable {}

class Animal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private House preferedHouse;
	Animal(String name, House h) {
		this.name = name;
		preferedHouse = h;
	}
	
	@Override
	public String toString() {
		return name + "[" + super.toString() +
				"], " + preferedHouse + "\n"; 
	}
	
}

public class MyWorld {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		House house = new House();
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal("Bosco the dog", house));
		animals.add(new Animal("Ralph the hamster", house));
		animals.add(new Animal("Molly the cat", house));
		
		System.out.println("Animals : " + animals);
		ByteArrayOutputStream buf1 = 
				new ByteArrayOutputStream();
		ObjectOutputStream out1 = new ObjectOutputStream(buf1);
		out1.writeObject(animals);
		out1.writeObject(animals);	// Write a 2nd set
		
		// Write to a different stream:
		ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
		ObjectOutputStream out2 = new ObjectOutputStream(buf2);
		out2.writeObject(animals);
		// Now get them back:
		ObjectInputStream in1 = new ObjectInputStream(
			new ByteArrayInputStream(buf1.toByteArray()));
		ObjectInputStream in2 = new ObjectInputStream(
				new ByteArrayInputStream(buf2.toByteArray()));
		List<Animal> animals1 = (List<Animal>) in1.readObject(),
				animals2 = (List<Animal>) in1.readObject(),
				animals3 = (List<Animal>) in2.readObject();
		System.out.println("animals1: " + animals1);
		System.out.println("animals2: " + animals2);
		System.out.println("animals3: " + animals3);
		
	}
}
