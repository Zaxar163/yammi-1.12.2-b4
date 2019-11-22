package ru.yammi.modulesystem.modules;

import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class StrafeModule extends Module {

	public StrafeModule() {
		super("Strafe", ModuleCategory.MOVEMENT);
		getValues().add(new SliderValue("Force", 0.0f, 1.0f, 1.0f));
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.player.hurtTime <= 0 && (mc.player.onGround || !mc.player.isInWater())) {
			float f = mc.player.rotationYaw;
			if (mc.player.moveForward < 0.0f)
				f += 180.0f;
			if (mc.player.moveStrafing > 0.0f)
				f -= 90.0f * (mc.player.moveForward > 0.0f ? 0.68f : mc.player.moveForward < 0.0f ? -0.5f : 1.0f);
			if (mc.player.moveStrafing < 0.0f)
				f += 90.0f * (mc.player.moveForward > 0.0f ? 0.68f : mc.player.moveForward < 0.0f ? -0.5f : 1.0f);
			double d = 0.221;
			if (mc.player.isSprinting())
				d *= 0.3190000119209289;
			if (mc.player.isSneaking())
				d *= 0.3;
			d = getFloatValue("Force");
			float f2 = (float) ((float) Math.cos((f + 90.0f) * 3.141592653589793 / 180.0) * d);
			float f3 = (float) ((float) Math.sin((f + 90.0f) * 3.141592653589793 / 180.0) * d);
			if (mc.gameSettings.keyBindForward.isKeyDown() || mc.gameSettings.keyBindLeft.isKeyDown()
					|| mc.gameSettings.keyBindRight.isKeyDown() || mc.gameSettings.keyBindBack.isKeyDown()) {
				mc.player.motionX = f2;
				mc.player.motionZ = f3;
			}
		}
	}
}
