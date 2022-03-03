package L7.alarm;

import java.util.LinkedList;

public class AlarmThread {
	private Thread thread;
	private long ms;
	private LinkedList <AlarmListener> list = new LinkedList<AlarmListener>();

	public AlarmThread(long ms) {
		this.ms = ms;
	}

	public void startAlarm() {
		if(thread==null) {
			thread = new AT();
			thread.start();
		}
	}
	public void addAlarmListener(AlarmListener listener){
		if (listener != null)
			list.add(listener);

	}

	private class AT extends Thread {
		public void run() {
			try {
				Thread.sleep(ms);
			}catch(InterruptedException e) {

			}
			for (AlarmListener al : list)
				al.alarm();
			thread = null;
		}
	}
}
