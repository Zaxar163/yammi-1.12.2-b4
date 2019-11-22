package ru.yammi.modulesystem.modules;

import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class SpiderModule extends Module {

	public SpiderModule() {
		super("Spider", ModuleCategory.MOVEMENT);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.player.collidedHorizontally)
			mc.player.motionY = 0.14;
	}
}
