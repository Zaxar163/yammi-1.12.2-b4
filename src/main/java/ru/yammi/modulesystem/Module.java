package ru.yammi.modulesystem;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;

public class Module {

	private List<BooleanValue> booleanValues = new ArrayList<>();
	private ModuleCategory category;
	private int keybind = 0;
	protected Minecraft mc = Minecraft.getMinecraft();
	private String name;
	private boolean state;
	private List<SliderValue> values = new ArrayList<>();

	public Module(String string, ModuleCategory xzSCDzduhxVFqcw) {
		name = string;
		category = xzSCDzduhxVFqcw;
	}

	public boolean getBooleanValue(String string) {
		for (BooleanValue tbIURGtAXLuntKY2 : getBooleanValues()) {
			if (!tbIURGtAXLuntKY2.getName().equals(string))
				continue;
			return tbIURGtAXLuntKY2.isState();
		}
		return false;
	}

	public List<BooleanValue> getBooleanValues() {
		return booleanValues;
	}

	public ModuleCategory getCategory() {
		return category;
	}

	public float getFloatValue(String string) {
		return getValue(string).getValue();
	}

	public int getKeybind() {
		return keybind;
	}

	public String getName() {
		return name;
	}

	public boolean getState() {
		return state;
	}

	public SliderValue getValue(String string) {
		for (SliderValue dBUsaySotRtyDdU : getValues()) {
			if (!dBUsaySotRtyDdU.getName().equals(string))
				continue;
			return dBUsaySotRtyDdU;
		}
		return null;
	}

	public List<SliderValue> getValues() {
		return values;
	}

	public void onDisable() {
	}

	public void onEnable() {
	}

	public void setCategory(ModuleCategory xzSCDzduhxVFqcw) {
		category = xzSCDzduhxVFqcw;
	}

	public void setKeybind(int n) {
		keybind = n;
	}

	public void setName(String string) {
		name = string;
	}

	public void setState(boolean bl) {
		state = bl;
	}
}
