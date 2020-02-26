package com.chapter18.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Controlling serialization by adding your own
 * writeObejct() and readObject() methods.
 * @author xqk
 * @date 2017年8月9日 下午6:39:50 
 * @version V1.0
 */
public class SerialCtl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String a;
	private transient String b;
	public SerialCtl(String a, String b) {
		this.a = "Not Transient: " + a;
		this.b = "Transient: " + b;
	}
	
	@Override
	public String toString() {
		return a + "\n" + b;
	}
	
	private void writeObject(ObjectOutputStream out)
	throws IOException {
		out.defaultWriteObject();
		out.writeObject(b);
	}
	
	private void readObject(ObjectInputStream in)
	throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		b = (String) in.readObject();
	}
	
	public static void main(String[] args) throws Exception {
		SerialCtl sc = new SerialCtl("Test1", "Test2");
		System.out.println("Before:\n" + sc);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(buf);
		out.writeObject(sc);
		
		ObjectInputStream in = new ObjectInputStream(
			new ByteArrayInputStream(buf.toByteArray()));
		SerialCtl sc2 = (SerialCtl) in.readObject();
		System.out.println("After:\n" + sc2);
	}

}
