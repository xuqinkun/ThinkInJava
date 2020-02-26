package com.chapter18.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferToText {
	private static final int SIZE = 1024;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String outfile = "tempfile/data2.txt";
		FileChannel fc = new FileOutputStream(outfile).getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes()));
		fc.close();
		fc = new FileInputStream(outfile).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(SIZE);
		fc.read(buffer);
		buffer.flip();
		
		System.out.println(buffer.asCharBuffer());
		// Decode using this system's default Charset:
		buffer.rewind();
		String encoding = System.getProperty("file.encoding");
		System.out.println("Decoded using " + encoding + ": " +
				Charset.forName(encoding).decode(buffer));
		// Or, we could encode with something that will print
		fc = new FileOutputStream(outfile).getChannel();
		fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
		fc.close();
		// Now try reading again:
		fc = new FileInputStream(outfile).getChannel();
		buffer.clear();
		fc.read(buffer);
		buffer.flip();
		System.out.println(buffer.asCharBuffer());
		fc = new FileOutputStream(outfile).getChannel();
		buffer = ByteBuffer.allocate(24);
		buffer.asCharBuffer().put("Some text");
		fc.write(buffer);
		fc.close();
		// Read and display:
		fc = new FileInputStream(outfile).getChannel();
		buffer.clear();
		fc.read(buffer);
		buffer.flip();
		System.out.println(buffer.asCharBuffer());
	}
}
