package ru.yammi.modulesystem.modules;

import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class MultiJumpModule extends Module {

	public MultiJumpModule() {
		super("MultiJump", ModuleCategory.MOVEMENT);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.gameSettings.keyBindJump.isKeyDown()) {
			mc.player.onGround = true;
			mc.player.setJumping(false);
		}
	}
}
