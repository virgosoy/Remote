package com.soy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Main {
	private static Socket socket = null;
	private static String title = null;

	public static void main(String[] args) {
		int input = JOptionPane.showConfirmDialog(null, "请选择是否以客户端方式运行，否为服务端。");
		switch (input) {
		case JOptionPane.YES_OPTION:
			title = "客户端";
			runClient();
			break;
		case JOptionPane.NO_OPTION:
			title = "服务端";
			runServer();
			break;
		case JOptionPane.CANCEL_OPTION:
			return;
		}
		input = JOptionPane.showConfirmDialog(null, "请选择是否以被控制端方式运行，否为控制端。",
				title, JOptionPane.YES_NO_CANCEL_OPTION);
		switch (input) {
		case JOptionPane.YES_OPTION:
			new UnderControl().run(socket);
			break;
		case JOptionPane.NO_OPTION:
			new Control().run(socket);
			break;
		case JOptionPane.CANCEL_OPTION:
			return;
		}
	}

	/**
	 * 运行服务端
	 */
	private static void runServer() {
		String input = JOptionPane.showInputDialog("监听端口", "1766");
		int port = Integer.parseInt(input);
		ServerSocket server;
		try {
			server = new ServerSocket(port);
			System.out.println("服务端已启动，等待客户端连接。端口：" + port);
			// 等待客户端连接
			socket = server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("启动失败，将关闭程序");
			System.exit(1);
		}
	}

	/**
	 * 运行客户端
	 */
	private static void runClient() {
		String input = JOptionPane.showInputDialog("服务端IP地址", "127.0.0.1:1766");
		if (input == null) {
			return;
		}
		String[] inputs = input.split(":");
		String host = inputs[0];
		int post = Integer.parseInt(inputs[1]);
		try {
			socket = new Socket(host, post);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("启动失败，将关闭程序");
			System.exit(1);
		}

	}

}
