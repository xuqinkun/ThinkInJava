package com.chapter19.enumerated;

import static util.Print.*;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;


enum Explore { HERE, THERE }

public class Reflection {
	public static Set<String> analyze(Class<?> enumClass) {
		print("-----Analyzing " + enumClass.getSimpleName() + "-----");
		print("Interfaces:");
		for(Type t : enumClass.getGenericInterfaces())
			print(t);
		print("Base: " + enumClass.getSuperclass());
		print("Methods: ");
		Set<String> methods = new TreeSet<String>();
		for(Method m : enumClass.getMethods())
			methods.add(m.getName());
		print(methods);
		return methods;
	}
	
	public static void main(String[] args) {
		Set<String> exploreMethods = analyze(Explore.class);
		Set<String> enumMethods = analyze(Enum.class);
		print("Explore.containsAll(Enum)? " + 
				exploreMethods.containsAll(enumMethods));
		printnb("Explore.removeAll(enum): ");
		exploreMethods.removeAll(enumMethods);
		print(exploreMethods);
		// Decompile the code for the enum:
		//OSExecute.command("javap Explore");
	}
}
