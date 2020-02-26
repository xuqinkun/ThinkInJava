package com.chapter18.io;

import java.util.ArrayList;

import nu.xom.*;

public class People extends ArrayList<Person> {

	private static final long serialVersionUID = 1L;

	public People(String fileName) throws Exception {
		Document doc = new Builder().build(fileName);
		Elements elements = doc.getRootElement().getChildElements();
		for(int i = 0; i < elements.size(); i++)
			add(new Person(elements.get(i)));
	}
	
	public static void main(String[] args) throws Exception {
		People people = new People("tempfile/People.xml");
		System.out.println(people);
	}

}
