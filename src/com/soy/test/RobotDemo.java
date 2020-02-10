package com.soy.test;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class RobotDemo {
	public RobotDemo() throws Exception{
		Robot robot = new Robot();
		//��������
		JFrame jframe = new JFrame();
		jframe.setTitle("��ͼ");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocationRelativeTo(null);
		jframe.setSize(500, 400);
		//��ǩ����
		JLabel lbl = new JLabel();
		Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		lbl.setIcon(new ImageIcon(robot.createScreenCapture(rec)));
		//�����������
		JScrollPane jscrollPane = new JScrollPane();
		jscrollPane.setViewportView(lbl);
		//��ʾ����
		jframe.add(jscrollPane);
		jframe.setVisible(true);
		//����ˢ��
		new Thread(new AutoRefrash(lbl,rec)).start();
		lbl.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent me) {
				// TODO Auto-generated method stub
				System.out.println("("+me.getX()+","+me.getY()+")");
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	public static void main(String[] args) throws Exception{
		new RobotDemo();
	}
}
class AutoRefrash implements Runnable{
	private static Robot robot = null;
	private Rectangle rec = null;
	private JLabel lbl;
	static{
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public AutoRefrash(){}
	public AutoRefrash(JLabel lbl, Rectangle rec){
		this.lbl = lbl;
		this.rec = rec;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			lbl.setIcon(new ImageIcon(robot.createScreenCapture(rec)));
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
