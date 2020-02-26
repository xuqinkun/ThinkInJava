package com.chapter18.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Demonstrate the "transient" password

public class Logon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date date = new Date();
	private String username;
	private transient String password;
	
	public Logon(String name, String pwd) {
		username = name;
		password = pwd;
	}
	
	@Override
	public String toString() {
		return "Logon info: \n username: " + username +
				"\n date: " + date + "\n passowrd: " + password;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ClassNotFoundException {
		Logon a = new Logon("Hulk", "myLittlePony");
		System.out.println("Logon a = " + a);
		String path = "tempfile/Logon.out";
		ObjectOutputStream out = new ObjectOutputStream(
			new FileOutputStream(path));
		out.writeObject(a);
		out.close();
		TimeUnit.SECONDS.sleep(1); // // Delay
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(path));
		System.out.println("Recovering obejct at " + new Date());
		a = (Logon) in.readObject();
		in.close();
		System.out.println("Logon a = " + a);
		
		
	}
}
