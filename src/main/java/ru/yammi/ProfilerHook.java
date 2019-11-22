package ru.yammi;

import net.minecraft.client.Minecraft;
import net.minecraft.profiler.Profiler;
import ru.yammi.eventsystem.EventBus;
import ru.yammi.eventsystem.events.Render2DEvent;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.eventsystem.events.UpdateEvent;

public class ProfilerHook extends Profiler {

	@Override
	public void startSection(String string) {
		if (string.equals("destroyProgress"))
			EventBus.call(new Render3DEvent());
		if (string.equals("gui")) {
			Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
			EventBus.call(new Render2DEvent());
		}
		if (string.contains("tick") && Minecraft.getMinecraft().player != null
				&& Minecraft.getMinecraft().world != null)
			EventBus.call(new UpdateEvent());
		super.startSection(string);
	}
}
