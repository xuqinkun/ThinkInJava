package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
	
	public static void getMusicPaths(List<File> fileList, File currentFile) {
        if (currentFile == null) return;        // 空文件直接返回
        else if (currentFile.isFile()) {         // 非文件夹
            Matcher matcher = Pattern.compile(".*mp3").matcher(currentFile.getName());
            if (matcher.matches()) {
                fileList.add(currentFile);
                return;
            }
        }
        else if (currentFile.isDirectory()) {
            File[] files = currentFile.listFiles();
            if (files == null || files.length == 0)
                return;
            for (File file : files) {
                getMusicPaths(fileList, file);
            }
        }

    }
	
	public static void main(String[] args) {
		File[] files = new File("D://").getAbsoluteFile().listFiles();
		
		ArrayList<File> fileList = new ArrayList<File>();
		getMusicPaths(fileList, new File("H://"));
		for (File file : fileList) {
			System.out.println(file.getAbsolutePath());
		}
	}
}
