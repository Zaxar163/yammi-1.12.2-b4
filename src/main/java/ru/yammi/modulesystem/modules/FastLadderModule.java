package ru.yammi.modulesystem.modules;

import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class FastLadderModule extends Module {

	public FastLadderModule() {
		super("FastLadder", ModuleCategory.MISC);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.player.isOnLadder())
			mc.player.motionY = 0.338;
	}
}
