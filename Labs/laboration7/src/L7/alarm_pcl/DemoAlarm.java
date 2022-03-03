package L7.alarm_pcl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DemoAlarm implements PropertyChangeListener {
	public DemoAlarm(int ms) {
		AlarmThread at = new AlarmThread(ms);
		at.addAlarmListener(this);
		at.startAlarm();
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("alarm")){
			System.out.println("ALARM!");
		}
	}

	public static void main(String[] args) {
		DemoAlarm da = new DemoAlarm(4000);
	}
}