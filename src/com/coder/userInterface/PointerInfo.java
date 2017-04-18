package com.coder.userInterface;
import java.awt.MouseInfo;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

/**
 * @author Coder ACJHP
 *
 */
public class PointerInfo {

	private Timer timer;
	private TimerTask task;
	/**
	 * WE NEED THIS FOR GETING MOUSE COORDINATE ON ALL 10 MILLISECOND
	 *
	 */
	public void getPoints(JLabel x, JLabel y) {
		timer = new Timer();
		task = new TimerTask() {
			
			@Override
			public void run() {

				double pointX = MouseInfo.getPointerInfo().getLocation().getX();
				double pointY = MouseInfo.getPointerInfo().getLocation().getY();
				x.setText("Current X : "+pointX);
				y.setText("Current Y : "+pointY);
			}
		};
		timer.schedule(task, 0,10);
	}
}
