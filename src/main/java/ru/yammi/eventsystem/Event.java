package ru.yammi.eventsystem;

public class Event {

	private boolean cancelled = false;

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean bl) {
		cancelled = bl;
	}
}
