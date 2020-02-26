package com.chapter18.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
	private static final int SIZE = 1024;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		FileChannel fc = 
				new FileOutputStream("tempfile/data.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		// Add to the end of the file:
		fc = new RandomAccessFile("tempfile/data.txt","rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		// Read the file:
		fc = new FileInputStream("tempfile/data.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(SIZE);
		fc.read(buff);
		buff.flip();	
		while(buff.hasRemaining())
			System.out.print((char)buff.get());
	}
}
