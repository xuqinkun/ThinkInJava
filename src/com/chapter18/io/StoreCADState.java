package com.chapter18.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Shape implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int RED = 1, BLUE = 2, GREEN = 3;
	private int xPos, yPos, dimension;
	private static Random rand = new Random(45);
	private static int counter = 0;
	public abstract void setColor(int newColor);
	public abstract int getColor();
	
	public Shape(int xPos, int yPos, int dimension) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.dimension = dimension;
	}
	
	@Override
	public String toString() {
		return getClass() +
			"color[" + getColor() + "] xPos["
			+ xPos +"] yPos[" + yPos + "] dim[" +
			dimension + "]\n";
	}
	
	public static Shape reandomFactory() {
		int x = rand.nextInt(100);
		int y = rand.nextInt(100);
		int dim = rand.nextInt(100);
		switch (counter++ % 3) {
		default:
		case 0: return new Circle(x, y, dim);
		case 1: return new Square(x, y, dim);
		case 2: return new Line(x, y, dim); 
		}
	}
}

class Circle extends Shape {
	private static final long serialVersionUID = 1L;

	private static int color = RED;
	
	public Circle(int xPos, int yPos, int dimension) {
		super(xPos, yPos, dimension);
	}
	
	public static void 
	serializeStaticState(ObjectOutputStream os)
	throws IOException { os.writeInt(color); }
	
	public static void 
	deserializeStaticState(ObjectInputStream os)
			throws IOException { color = os.readInt(); }

	@Override
	public void setColor(int newColor) {
		color = newColor;
	}

	@Override
	public int getColor() {
		return color;
	}
}

class Square extends Shape {
	private static final long serialVersionUID = 1L;
	
	private static int color;
	public Square(int xPos, int yPos, int dimension) {
		super(xPos, yPos, dimension);
		color = RED; 
	}

	@Override
	public void setColor(int newColor) {
		color = newColor;
	}

	@Override
	public int getColor() {
		return color;
	}
	
	public static void 
	serializeStaticState(ObjectOutputStream os)
	throws IOException { os.writeInt(color); }
	
	public static void 
	deserializeStaticState(ObjectInputStream os)
			throws IOException { color = os.readInt(); }
}

class Line extends Shape {
	private static final long serialVersionUID = 1L;
	
	public static void 
	serializeStaticState(ObjectOutputStream os)
	throws IOException { os.writeInt(color); }
	
	public static void 
	deserializeStaticState(ObjectInputStream os)
			throws IOException { color = os.readInt(); }
	
	private static int color = RED;
	public Line(int xPos, int yPos, int dimension) {
		super(xPos, yPos, dimension);
	}
	
	@Override
	public void setColor(int newColor) {
		color = newColor;
	}

	@Override
	public int getColor() {
		return color;
	}
}

public class StoreCADState {
	public static void main(String[] args) throws Exception {
		/*List<Class<? extends Shape>> shapeTypes = 
				new ArrayList<Class<? extends Shape>>();
		// Add references to the class objects:
		shapeTypes.add(Circle.class);
		shapeTypes.add(Square.class);
		shapeTypes.add(Line.class);*/
		
		List<Shape> shapes = new ArrayList<Shape>();
		// Make some shapes
		for(int i = 0; i < 10; i++) 
			shapes.add(Shape.reandomFactory());
		
		// Set all the static colors to GREEN:
		for(int i = 0; i < shapes.size(); i++)
			shapes.get(i).setColor(Shape.GREEN);
		
		// Save the state vector:
		String path = "tempfile/CADState.out";
		ObjectOutputStream out = new ObjectOutputStream(
			new FileOutputStream(path));
		//out.writeObject(shapeTypes);
		Circle.serializeStaticState(out);
		Square.serializeStaticState(out);
		Line.serializeStaticState(out);
		out.writeObject(shapes);
		
		// Display the shapes:
		System.out.println(shapes);
	}
}
