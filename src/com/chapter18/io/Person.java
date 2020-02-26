package com.chapter18.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

/**
 * Use the XOM library to write and  read XML
 * @author xqk
 * @date 2017年8月9日 下午8:09:15 
 * @version V1.0
 */
public class Person {
	private String first, last;

	public Person(String first, String last) {
		this.first = first;
		this.last = last;
	}
	
	// Produce an XML Element from this Person object:
	public Element getXML() {
		Element person = new Element("person");
		Element firstName = new Element("first");
		firstName.appendChild(first);
		Element lastName = new Element("last");
		lastName.appendChild(last);
		person.appendChild(firstName);
		person.appendChild(lastName);
		return person;
	}
	
	// Constructor to restore a Person from an XML Element:
	public Person(Element person) {
		first = person.getFirstChildElement("first").getValue();
		last = person.getFirstChildElement("last").getValue();
	}
	
	public String toString() {
		return first + " " + last;
	}
	
	// Make it humen-readable
	public static void 
	format(OutputStream os, Document doc) throws Exception {
		Serializer s = new Serializer(os, "ISO-8859-1");
		s.setIndent(4);
		s.setMaxLength(60);
		s.write(doc);
		s.flush();
	}
	
	public static void main(String[] args) throws Exception {
		List<Person> people = Arrays.asList(
				new Person("Dr. Bunsen", "Honeydew"),
				new Person("Gonzo","The Great"),
				new Person("Phillip j.", "Fry"));
		System.out.println(people);
		Element root = new Element("people");
		for (Person p : people) {
			root.appendChild(p.getXML());
		}
		Document doc = new Document(root);
		format(System.out, doc);
		format(new BufferedOutputStream(
				new FileOutputStream("tempfile/People.xml")), doc);
	}
}
