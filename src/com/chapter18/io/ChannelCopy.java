package com.chapter18.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {
	private static final int SIZE = 1024;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		String src = "tempfile/Data.txt", 
			   dest = "tempfile/DataCopy.txt";
		FileChannel in = new FileInputStream(src).getChannel(),
				out = new FileOutputStream(dest).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(SIZE);
		while((in.read(buffer)) != -1) {
			buffer.flip();
			out.write(buffer);
			buffer.clear();
		}
		in.close();
		out.close();
	}
}
