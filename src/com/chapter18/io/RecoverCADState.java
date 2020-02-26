package com.chapter18.io;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		String path = "tempfile/CADState.out";
		ObjectInputStream in = new ObjectInputStream(
			new FileInputStream(path));

		Circle.deserializeStaticState(in);
		Square.deserializeStaticState(in);
		Line.deserializeStaticState(in);
		List<Shape> shapes = (List<Shape>) in.readObject();
		
		System.out.println(shapes);
	}
}
