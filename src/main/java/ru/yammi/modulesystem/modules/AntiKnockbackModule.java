package ru.yammi.modulesystem.modules;

import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class AntiKnockbackModule extends Module {

	public AntiKnockbackModule() {
		super("AntiKnockback", ModuleCategory.COMBAT);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.player.hurtResistantTime > 0 && mc.player.hurtTime > 0) {
			mc.player.hurtResistantTime = 0;
			mc.player.hurtTime = 0;
			mc.player.motionX = 0.0;
			mc.player.motionY = 0.0;
			mc.player.motionZ = 0.0;
		}
	}
}
