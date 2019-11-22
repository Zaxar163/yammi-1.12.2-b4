package ru.yammi.config;

public class BooleanValue {

	private String name;
	private boolean state;

	public BooleanValue(String string) {
		name = string;
		setState(false);
	}

	public String getName() {
		return name;
	}

	public boolean isState() {
		return state;
	}

	public void setName(String string) {
		name = string;
	}

	public void setState(boolean bl) {
		state = bl;
	}
}
