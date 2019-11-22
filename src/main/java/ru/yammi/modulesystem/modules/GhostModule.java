package ru.yammi.modulesystem.modules;

import net.minecraft.network.play.client.CPacketPlayer;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class GhostModule extends Module {

	public GhostModule() {
		super("Ghost", ModuleCategory.MISC);
	}

	@Override
	public void onDisable() {
		mc.player.noClip = false;
		mc.getConnection()
				.sendPacket(new CPacketPlayer.PositionRotation(mc.player.posX, mc.player.getEntityBoundingBox().minY,
						mc.player.posZ, mc.player.cameraYaw, mc.player.cameraPitch, mc.player.onGround));
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState()) {
			float f;
			mc.player.noClip = true;
			mc.player.fallDistance = 0.0f;
			mc.player.onGround = true;
			mc.player.capabilities.isFlying = false;
			mc.player.motionX = 0.0;
			mc.player.motionY = 0.0;
			mc.player.motionZ = 0.0;
			mc.player.jumpMovementFactor = f = 0.2f;
			if (mc.gameSettings.keyBindJump.isKeyDown())
				mc.player.motionY += f;
			if (mc.gameSettings.keyBindSneak.isKeyDown())
				mc.player.motionY -= f;
		}
	}
}
