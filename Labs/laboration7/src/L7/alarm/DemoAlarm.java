package L7.alarm;

public class DemoAlarm implements AlarmListener {
	public DemoAlarm(int ms) {
		AlarmThread at = new AlarmThread(ms);
		at.startAlarm();
		at.addAlarmListener(this);
		at.addAlarmListener(new WakeUpPrinter());
	}
	@Override
	public void alarm() {
		System.out.println("ALARM!");
	}
	public class WakeUpPrinter implements AlarmListener{

		@Override
		public void alarm() {
			System.out.println("WAKE UP!");
		}
	}
	public static void main(String[] args) {
		DemoAlarm da = new DemoAlarm(4000);
	}



}
