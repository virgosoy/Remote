package com.soy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 控制端
 * 
 * @author Soy
 * 
 */
public class Control {
	private ControlWindow clientWindow = null;
	private DataInputStream dataIn = null;
	private ObjectOutputStream objOut = null;

	/**
	 * 启动客户端
	 * 
	 * @param host
	 *            主机地址
	 * @param port
	 *            端口号
	 */
	public void run(Socket socket) {

		InputStream in = null;
		OutputStream out = null;
		try {
			// 获取Socket输入输出流
			in = socket.getInputStream();
			out = socket.getOutputStream();
			// 输入流封装成数据输入流d
			dataIn = new DataInputStream(in);
			// 输出流封装成对象输出流
			objOut = new ObjectOutputStream(out);
			// 创建窗体
			clientWindow = new ControlWindow(objOut);
			// 读取图片
			readImage();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取图片
	 * 
	 * @throws IOException
	 */
	private void readImage() throws IOException {
		while (true) {
			// 获取图片大小
			byte[] image = new byte[dataIn.readInt()];
			// 获取图片
			dataIn.readFully(image);
			// 图片显示到label
			clientWindow.showImage(image);
		}
	}
}
