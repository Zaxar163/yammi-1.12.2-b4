package ru.yammi.modulesystem.modules;

import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class FlyModule extends Module {

	public FlyModule() {
		super("Fly", ModuleCategory.MOVEMENT);
		getValues().add(new SliderValue("Speed", 0.0f, 10.0f, 0.1f));
	}

	@Override
	public void onDisable() {
		mc.player.capabilities.allowFlying = false;
		mc.player.capabilities.isFlying = false;
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState()) {
			mc.player.capabilities.allowFlying = true;
			mc.player.capabilities.isFlying = true;
			float f = getFloatValue("Speed");
			float f2 = f / 5.0f * 0.9f;
			mc.player.capabilities.setFlySpeed(0.35f * f2);
			mc.player.motionY = mc.gameSettings.keyBindJump.isKeyDown() ? (mc.player.motionY += 0.04)
					: (mc.player.motionY -= 0.005);
			if (mc.gameSettings.keyBindSneak.isKeyDown())
				mc.player.motionY -= 0.06;
		}
	}
}
