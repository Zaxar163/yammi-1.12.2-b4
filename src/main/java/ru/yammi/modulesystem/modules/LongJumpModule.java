package ru.yammi.modulesystem.modules;

import net.minecraft.util.math.MathHelper;
import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class LongJumpModule extends Module {

	public LongJumpModule() {
		super("LongJump", ModuleCategory.MOVEMENT);
		getValues().add(new SliderValue("Force", 0.0f, 100.0f, 1.0f));
	}

	public float getDirection() {
		float f = mc.player.rotationYaw;
		float f2 = mc.player.moveForward;
		float f3 = mc.player.moveStrafing;
		f += f2 < 0.0f ? 180 : 0;
		if (f3 < 0.0f)
			f += f2 < 0.0f ? -45 : f2 == 0.0f ? 90 : 45;
		if (f3 > 0.0f)
			f -= f2 < 0.0f ? -45 : f2 == 0.0f ? 90 : 45;
		return f * 0.017453292f;
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.gameSettings.keyBindJump.isKeyDown()) {
			float f = getFloatValue("Force");
			f = 100.0f - f + 1.0f;
			mc.player.setSprinting(true);
			double d = Math.sqrt(
					mc.player.motionX * mc.player.motionX + mc.player.motionZ * mc.player.motionZ + 10.0 / (10.0 * f));
			mc.player.motionX = -MathHelper.sin(getDirection()) * d;
			mc.player.motionZ = MathHelper.cos(getDirection()) * d;
			if (mc.player.onGround) {
				mc.player.jump();
				mc.player.motionY *= 0.94356256;
			} else if (mc.player.isAirBorne && !mc.player.onGround) {
				double d2 = Math.sqrt(mc.player.motionX * mc.player.motionX + mc.player.motionZ * mc.player.motionZ
						+ 10.0 / (10.0 * f)) + f / (f * 1000.0f);
				mc.player.motionX = -MathHelper.sin(getDirection()) * d2;
				mc.player.motionZ = MathHelper.cos(getDirection()) * d2;
			}
		}
	}
}
