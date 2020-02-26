package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import com.chapter18.io.Constant;

@SuppressWarnings("serial")
public class TextFile extends ArrayList<String> {
	// Read a file as a single string:
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(
				new File(fileName).getAbsoluteFile()));
			
			try {
				String s;
				while ((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");

				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return sb.toString();
	}
	
	// Write a single file in one method call:
	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(
				new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Read a file, split by any regular exoression:
	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		if(get(0).equals(""))
			remove(0);
	}
	
	// Normally read by lines:
	public TextFile(String fileName) {
		this(fileName, "\n");
	}
	
	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(
				new File(fileName).getAbsoluteFile());
			try {
				for (String item : this) {
					out.println(item);
				} 
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		String fileName = Constant.UTIL_PATH + "TextFile.java";
		String file = read(fileName);
		//System.out.println(file);
		String path = "tempfile/test.txt";
		String path2 = "tempfile/test2.txt";
		write(path, file);
		TextFile text = new TextFile(path);
		text.write(path2);
		// Break into unique sorted list of words:
		TreeSet<String> words = new TreeSet<String>(
			new TextFile(fileName,"\\W+"));
		// Display the capitalized words:
		System.out.println(words.headSet("a"));
	}

}
