package com.soy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * �����ƶ� 2���̣߳� 1.��ͣ�����ͻ��˷�ͼƬ 2.��ͣ�Ĵӿͻ��˻�ȡ�¼�������
 * 
 * @author Soy
 * 
 */
public class UnderControl {
	/**
	 * ���������ƶ�
	 * 
	 * @param socket
	 *            �׽���
	 */
	public void run(Socket socket) {
		InputStream in = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		ObjectInputStream ois = null;
		try {

			// ��ȡSocket���������
			in = socket.getInputStream();
			out = socket.getOutputStream();
			// ͼƬ������
			dos = new DataOutputStream(out);
			// �¼�������
			ois = new ObjectInputStream(in);
			// ���������߳�
			Thread imageThread = new Thread(new ImageThread(dos));
			imageThread.start();
			// ������ȡ�¼��߳�
			Thread eventThread = new Thread(new EventThread(ois));
			eventThread.start();
			// �ȴ����߳̽���
			imageThread.join();
			eventThread.join();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (dos != null) {
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
