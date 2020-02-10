package com.soy;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;


/**
 * 获取事件的线程
 * @author Soy
 *
 */
public class EventThread implements Runnable {
	private ObjectInputStream objIn;
	private Robot robot;

	public EventThread(ObjectInputStream objIn) {
		this.objIn = objIn;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				InputEvent event = (InputEvent) objIn.readObject();
				// 触发事件
				dealEvent(event);
				System.out.println(event);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 处理事件对象
	 * 
	 * @param event
	 *            要处理的事件对象
	 */
	private void dealEvent(InputEvent event) {
		if (event instanceof KeyEvent) {
			KeyEvent keyEvent = (KeyEvent) event;
			switch (keyEvent.getID()) {
			case KeyEvent.KEY_PRESSED:
				robot.keyPress(keyEvent.getKeyCode());
				break;
			case KeyEvent.KEY_RELEASED:
				robot.keyRelease(keyEvent.getKeyCode());
				break;
			}
		} else if (event instanceof MouseEvent) {
			MouseEvent mouseEvent = (MouseEvent) event;
			switch (mouseEvent.getID()) {
			case MouseEvent.MOUSE_PRESSED:
				robot.mousePress(getMouseButton(mouseEvent.getButton()));
				break;
			case MouseEvent.MOUSE_RELEASED:
				robot.mouseRelease(getMouseButton(mouseEvent.getButton()));
				break;
			case MouseEvent.MOUSE_MOVED:
				robot.mouseMove(mouseEvent.getX(), mouseEvent.getY());
				break;
			case MouseEvent.MOUSE_DRAGGED:
				robot.mouseMove(mouseEvent.getX(), mouseEvent.getY());
				break;
			}
		}
	}

	/**
	 * 将MouseEvent的Button转为InputEvent的Button值。
	 * 
	 * @param botton
	 * @return
	 */
	private int getMouseButton(int button) {
		switch (button) {
		case MouseEvent.BUTTON1:
			return InputEvent.BUTTON1_MASK;
		case MouseEvent.BUTTON2:
			return InputEvent.BUTTON2_MASK;
		case MouseEvent.BUTTON3:
			return InputEvent.BUTTON3_MASK;
		default:
			return -1;
		}

	}
}
