package com.soy;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 被控制端 2个线程： 1.不停的往客户端发图片 2.不停的从客户端获取事件并处理
 * 
 * @author Soy
 * 
 */
public class UnderControl {
	/**
	 * 启动被控制端
	 * 
	 * @param socket
	 *            套接字
	 */
	public void run(Socket socket) {
		InputStream in = null;
		OutputStream out = null;
		DataOutputStream dos = null;
		ObjectInputStream ois = null;
		try {

			// 获取Socket输入输出流
			in = socket.getInputStream();
			out = socket.getOutputStream();
			// 图片数据流
			dos = new DataOutputStream(out);
			// 事件对象流
			ois = new ObjectInputStream(in);
			// 启动截屏线程
			Thread imageThread = new Thread(new ImageThread(dos));
			imageThread.start();
			// 启动获取事件线程
			Thread eventThread = new Thread(new EventThread(ois));
			eventThread.start();
			// 等待子线程结束
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
