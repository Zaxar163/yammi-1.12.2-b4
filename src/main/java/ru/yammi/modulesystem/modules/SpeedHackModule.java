package ru.yammi.modulesystem.modules;

import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class SpeedHackModule extends Module {

	public SpeedHackModule() {
		super("SpeedHack", ModuleCategory.MOVEMENT);
		getValues().add(new SliderValue("Speed", 0.0f, 100.0f, 1.0f));
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.player.onGround)
			if (!(mc.player.isSneaking() || mc.player.moveForward == 0.0f && mc.player.moveStrafing == 0.0f)) {
				@SuppressWarnings("unused")
				float f;
				double d;
				if (mc.player.moveForward > 0.0f && !mc.player.collidedHorizontally)
					mc.player.setSprinting(true);
				mc.player.motionX *= 5.0;
				mc.player.motionZ *= 5.0;
				double d2 = Math.sqrt(Math.pow(mc.player.motionX, 2.0) + Math.pow(mc.player.motionZ, 2.0));
				if (d2 > (d = (f = getFloatValue("Speed")) / 5.0 * 0.8)) {
					mc.player.motionX = mc.player.motionX / d2 * d;
					mc.player.motionZ = mc.player.motionZ / d2 * d;
				}
			} else {
				mc.player.motionX = 0.0;
				mc.player.motionZ = 0.0;
			}
	}
}
