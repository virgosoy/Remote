package com.soy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * ���ƶ�
 * 
 * @author Soy
 * 
 */
public class Control {
	private ControlWindow clientWindow = null;
	private DataInputStream dataIn = null;
	private ObjectOutputStream objOut = null;

	/**
	 * �����ͻ���
	 * 
	 * @param host
	 *            ������ַ
	 * @param port
	 *            �˿ں�
	 */
	public void run(Socket socket) {

		InputStream in = null;
		OutputStream out = null;
		try {
			// ��ȡSocket���������
			in = socket.getInputStream();
			out = socket.getOutputStream();
			// ��������װ������������d
			dataIn = new DataInputStream(in);
			// �������װ�ɶ��������
			objOut = new ObjectOutputStream(out);
			// ��������
			clientWindow = new ControlWindow(objOut);
			// ��ȡͼƬ
			readImage();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @throws IOException
	 */
	private void readImage() throws IOException {
		while (true) {
			// ��ȡͼƬ��С
			byte[] image = new byte[dataIn.readInt()];
			// ��ȡͼƬ
			dataIn.readFully(image);
			// ͼƬ��ʾ��label
			clientWindow.showImage(image);
		}
	}
}
