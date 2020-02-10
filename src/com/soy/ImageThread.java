package com.soy;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 发送图片的线程
 * @author Soy
 *
 */
public class ImageThread implements Runnable {
	// Socket输出流：用来输出图片
	private DataOutputStream dataOut;
	// 截屏需要的Robot
	private Robot robot;

	public ImageThread(DataOutputStream out) {
		this.dataOut = out;
	}

	@Override
	public void run() {
		try {
			robot = new Robot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 获取系统默认工具包
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// 获取屏幕大小
		Dimension dm = toolkit.getScreenSize();
		Rectangle rect = new Rectangle(dm);
		try {

			while (true) {
				// 开始截图
				BufferedImage bufImg = robot.createScreenCapture(rect);
				// 图片字节数组输出流
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				// 获取JPEG编码器
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(byteOut);
				// 使用编码器进行图片压缩
				encoder.encode(bufImg);
				// 压缩后的图片数据输出到字节数组
				byte[] image = byteOut.toByteArray();
				// 将图片大小输出到数据输出流
				dataOut.writeInt(image.length);
				// 将图片输出到数据输出流。
				dataOut.write(image);
				// 手动刷新缓存
				// dataOut.flush();
				// 延时
				Thread.sleep(100);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("客户端端开链接");
		} finally {
			if (dataOut != null) {
				try {
					dataOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
