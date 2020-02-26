package com.chapter21.concurrency;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		InputStream socketInput = 
				new Socket("localhost",8080).getInputStream();
		InputStream in = System.in;
		exec.execute(new IOBlocked(socketInput));
		exec.execute(new IOBlocked(in));
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Shutting down all threads");
		exec.shutdownNow();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + socketInput.getClass().getSimpleName());
		
		try {
			socketInput.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Closing " + in.getClass().getName());
		in.close();
	}
}
