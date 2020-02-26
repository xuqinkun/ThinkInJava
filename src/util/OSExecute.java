package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;


public class OSExecute {
	public static void command(String command) {
		boolean err = false;
		try{
			Process process = 
				new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(
				new InputStreamReader(process.getInputStream()));
			String s;
			while((s = results.readLine()) != null) 
				System.out.println(s);
			BufferedReader errors = new BufferedReader(
				new InputStreamReader(process.getErrorStream()));
			
			// Report errores and return nonzero value
			// to calling process if there are problems:
			while((s = errors.readLine()) != null) {
				System.err.println(s);
				err = true;
			}
		} catch (Exception e) {
			if(!command.startsWith("CMD /C"))
				command("CMD /c " + command);
			else
				throw new RuntimeException(e);
		}
		if(err)
			throw new OSExecuteException("Errors executing " + command);
	}
	
	public static void main(String[] args) {
		String name = new OSExecute().getClass().getSimpleName();
		String filename = "bin/util/";
		File file = new File(filename);
		String path = file.getAbsolutePath();
		System.out.println(path);
		OSExecute.command("d:\n");
		OSExecute.command("cd "+ path +" \n");
		OSExecute.command("javap "+ name);
	}
}
