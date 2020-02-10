package com.soy;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


/**
 * 控制端窗口
 * @author Soy
 *
 */
public class ControlWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9204307994939837993L;
	private JLabel lbl;
	private ObjectOutputStream objOut;

	/**
	 * @param objOut
	 *            事件对象输出流
	 */
	public ControlWindow(ObjectOutputStream objOut) {
		// 设置数据
		this.objOut = objOut;
		// 设置窗体
		setTitle("远程控制客户端");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置label
		lbl = new JLabel();
		// 设置滑动窗格
		JScrollPane jscrollPane = new JScrollPane(lbl);
		// 窗格添加到窗体
		add(jscrollPane);
		// 窗体进行键盘监听
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				sendEvent(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				sendEvent(e);
			}
		});
		// JLabel进行鼠标监听
		lbl.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				sendEvent(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				sendEvent(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		lbl.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				sendEvent(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				sendEvent(e);
			}
		});

		// 显示窗体
		setVisible(true);

	}

	/**
	 * 显示图片
	 * 
	 * @param image
	 *            要显示的图片
	 */
	public void showImage(byte[] image) {

		lbl.setIcon(new ImageIcon(image));
		// this.repaint();

	}

	/**
	 * 发送事件
	 * 
	 * @param event
	 *            要发送的事件
	 */
	public void sendEvent(InputEvent event) {
		try {
			objOut.writeObject(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
