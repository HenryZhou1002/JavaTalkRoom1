package com.hwaphon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ServerClient {

	/**
	 * @param args
	 */
	
	private ArrayList<PrintWriter> mWriters;
	
	/*
	 * 内部类 MyHandler，实现Runnable接口
	 */
	public class MyHandle implements Runnable{
		
		Socket mSocket;//导入socket包
		BufferedReader mReader;
		
		public MyHandle(Socket socket){//构造函数
			mSocket = socket;
			try {
				InputStreamReader reader = new InputStreamReader(
						mSocket.getInputStream());
				mReader = new BufferedReader(reader);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		@Override
		public void run() {
			String message = "";
			
			try {
				while((message = mReader.readLine()) != null){
					tellEveryone(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void start(){
		mWriters = new ArrayList<>();
		
		try {
			ServerSocket serverSocket = new ServerSocket(5000);
			
			while (true){
				Socket socket = serverSocket.accept();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void tellEveryone(String message){
		Iterator<PrintWriter> iterator = mWriters.iterator();
		while(iterator.hasNext()){
			PrintWriter writer = iterator.next();
			writer.println(message);
			writer.flush();
		}
	}
	

	public static void main(String[] args) {
		new ServerClient().start();

	}

}
