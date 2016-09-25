package com.hwaphon;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import com.hwaphon.ServerClient.MyHandle;

public class SocketClient {
	private JFrame mFrame;
	private JPanel mPane;
	private JTextArea mArea;
	private JScrollPane mScrollPane;
	private JButton mButton;
	private JTextField mField;
	
	
	private Socket mSocket;
	private PrintWriter mWriter;
	private BufferedReader mReader;
	
	public SocketClient(){
		setUI();
		setNetWork();
		new Thread(new IncomingReader()).start();
	}

	private void setNetWork() {
		try {
			mSocket = new Socket("127.0.0.1",5000);
			mWriter = new PrintWriter(mSocket.getOutputStream());
			mReader = new BufferedReader(new InputStreamReader(
					mSocket.getInputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void setUI() {
		mButton = new JButton("send");
		mButton.setFocusable(false);
		mButton.setBackground(Color.WHITE);
		// mButton.addActionListener(new SendListner());
		
		mField = new JTextField(20);
		
		mArea = new JTextArea(6,18);
		
	}
}
