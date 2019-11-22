package ru.yammi.helpers;

public class MSTimer {

	private long currentMS = 0L;
	private long pause = 0L;

	public MSTimer(long l) {
		pause = l;
		currentMS = System.currentTimeMillis();
	}

	public boolean checkMS() {
		if (currentMS <= System.currentTimeMillis()) {
			currentMS = System.currentTimeMillis() + pause;
			return true;
		}
		return false;
	}
}
