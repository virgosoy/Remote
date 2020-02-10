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
 * ����ͼƬ���߳�
 * @author Soy
 *
 */
public class ImageThread implements Runnable {
	// Socket��������������ͼƬ
	private DataOutputStream dataOut;
	// ������Ҫ��Robot
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

		// ��ȡϵͳĬ�Ϲ��߰�
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// ��ȡ��Ļ��С
		Dimension dm = toolkit.getScreenSize();
		Rectangle rect = new Rectangle(dm);
		try {

			while (true) {
				// ��ʼ��ͼ
				BufferedImage bufImg = robot.createScreenCapture(rect);
				// ͼƬ�ֽ����������
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				// ��ȡJPEG������
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(byteOut);
				// ʹ�ñ���������ͼƬѹ��
				encoder.encode(bufImg);
				// ѹ�����ͼƬ����������ֽ�����
				byte[] image = byteOut.toByteArray();
				// ��ͼƬ��С��������������
				dataOut.writeInt(image.length);
				// ��ͼƬ����������������
				dataOut.write(image);
				// �ֶ�ˢ�»���
				// dataOut.flush();
				// ��ʱ
				Thread.sleep(100);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�ͻ��˶˿�����");
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
