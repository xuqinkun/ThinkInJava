package com.chapter18.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BinaryFile {
	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bf = new BufferedInputStream(
			new FileInputStream(bFile));
		try {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		} finally {
			bf.close();
		}
	}
	
	public static byte[] read(String bFile) throws IOException {
		return read(new File(bFile).getAbsoluteFile());
	}
	
	public static void main(String[] args) throws IOException {
		byte[] data = read("tempfile/test2.txt");
		String text = new String(data);
		String[] words = text.split("\\W+");
		Map<String,Integer> map = new HashMap<String, Integer>();
		for (String word : words) {
			if(!map.containsKey(word))
				map.put(word, 1);
			else
				map.put(word, map.get(word) + 1);
		}
		System.out.println(map);
	}
}
