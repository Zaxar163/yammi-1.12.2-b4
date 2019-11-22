package ru.yammi.config;

public class SliderValue {

	private boolean hovered = false;
	private float maxValue;
	private float minValue;
	private float modifyValue;
	private int mouseX = 0;
	private String name;
	private int sliderX = 0;
	private float value;

	public SliderValue(String string, float f, float f2, float f3) {
		setName(string);
		setMinValue(f);
		setMaxValue(f2);
		setModifyValue(f3);
		setValue(getMinValue());
	}

	public float getMaxValue() {
		return maxValue;
	}

	public float getMinValue() {
		return minValue;
	}

	public float getModifyValue() {
		return modifyValue;
	}

	public int getMouseX() {
		return mouseX;
	}

	public String getName() {
		return name;
	}

	public int getSliderX() {
		return sliderX;
	}

	public float getValue() {
		return value;
	}

	public boolean isHovered() {
		return hovered;
	}

	public void setHovered(boolean bl) {
		hovered = bl;
	}

	public void setMaxValue(float f) {
		maxValue = f;
	}

	public void setMinValue(float f) {
		minValue = f;
	}

	public void setModifyValue(float f) {
		modifyValue = f;
	}

	public void setMouseX(int n) {
		mouseX = n;
	}

	public void setName(String string) {
		name = string;
	}

	public void setSliderX(int n) {
		sliderX = n;
	}

	public void setValue(float f) {
		value = f;
	}
}
