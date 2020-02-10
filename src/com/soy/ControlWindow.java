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
 * ���ƶ˴���
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
	 *            �¼����������
	 */
	public ControlWindow(ObjectOutputStream objOut) {
		// ��������
		this.objOut = objOut;
		// ���ô���
		setTitle("Զ�̿��ƿͻ���");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ����label
		lbl = new JLabel();
		// ���û�������
		JScrollPane jscrollPane = new JScrollPane(lbl);
		// ������ӵ�����
		add(jscrollPane);
		// ������м��̼���
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
		// JLabel����������
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

		// ��ʾ����
		setVisible(true);

	}

	/**
	 * ��ʾͼƬ
	 * 
	 * @param image
	 *            Ҫ��ʾ��ͼƬ
	 */
	public void showImage(byte[] image) {

		lbl.setIcon(new ImageIcon(image));
		// this.repaint();

	}

	/**
	 * �����¼�
	 * 
	 * @param event
	 *            Ҫ���͵��¼�
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
