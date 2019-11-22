package ru.yammi.modulesystem.modules;

import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class FullbrightModule extends Module {

	public FullbrightModule() {
		super("Fullbright", ModuleCategory.RENDER);
	}

	@EventTarget
	public void onRender3D(Render3DEvent ytROIMjqOZSKSkb2) {
		mc.gameSettings.gammaSetting = getState() ? 200.0f : 0.5f;
	}
}
