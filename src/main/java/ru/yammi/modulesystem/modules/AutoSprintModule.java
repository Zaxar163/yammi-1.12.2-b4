package ru.yammi.modulesystem.modules;

import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class AutoSprintModule extends Module {

	public AutoSprintModule() {
		super("AutoSprint", ModuleCategory.MOVEMENT);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.gameSettings.keyBindForward.isKeyDown())
			mc.player.setSprinting(true);
	}
}
