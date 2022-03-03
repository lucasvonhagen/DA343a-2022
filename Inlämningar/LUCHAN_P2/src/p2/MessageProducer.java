package p2;

public interface MessageProducer {
	int delay();
	int times();
	public int size();
	public Message nextMessage();
	
	default void info() {
		System.out.println("times="+times()+", delay="+delay()+", size="+size()+"]");
	}
}
