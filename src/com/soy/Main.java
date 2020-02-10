package com.soy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Main {
	private static Socket socket = null;
	private static String title = null;

	public static void main(String[] args) {
		int input = JOptionPane.showConfirmDialog(null, "��ѡ���Ƿ��Կͻ��˷�ʽ���У���Ϊ����ˡ�");
		switch (input) {
		case JOptionPane.YES_OPTION:
			title = "�ͻ���";
			runClient();
			break;
		case JOptionPane.NO_OPTION:
			title = "�����";
			runServer();
			break;
		case JOptionPane.CANCEL_OPTION:
			return;
		}
		input = JOptionPane.showConfirmDialog(null, "��ѡ���Ƿ��Ա����ƶ˷�ʽ���У���Ϊ���ƶˡ�",
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
	 * ���з����
	 */
	private static void runServer() {
		String input = JOptionPane.showInputDialog("�����˿�", "1766");
		int port = Integer.parseInt(input);
		ServerSocket server;
		try {
			server = new ServerSocket(port);
			System.out.println("��������������ȴ��ͻ������ӡ��˿ڣ�" + port);
			// �ȴ��ͻ�������
			socket = server.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("����ʧ�ܣ����رճ���");
			System.exit(1);
		}
	}

	/**
	 * ���пͻ���
	 */
	private static void runClient() {
		String input = JOptionPane.showInputDialog("�����IP��ַ", "127.0.0.1:1766");
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
			System.out.println("����ʧ�ܣ����رճ���");
			System.exit(1);
		}

	}

}
