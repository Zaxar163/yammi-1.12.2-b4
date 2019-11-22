package ru.yammi.modulesystem.modules;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.helpers.MSTimer;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class VClipModule extends Module {

	private MSTimer msTimer = new MSTimer(20L);

	public VClipModule() {
		super("VClip", ModuleCategory.MOVEMENT);
		getBooleanValues().add(new BooleanValue("Down"));
		getValues().add(new SliderValue("Motion Y", 0.0f, 1.0f, 0.1f));
		getValues().add(new SliderValue("Teleport Y", 0.0f, 5.0f, 0.1f));
	}

	private boolean isBelowBlock() {
		float f = 1.0f;
		for (float f2 = -f; f2 <= f; f2 += 1.0f) {
			int n = (int) Minecraft.getMinecraft().player.posX;
			int n2 = (int) (Minecraft.getMinecraft().player.posY + f2);
			int n3 = (int) Minecraft.getMinecraft().player.posZ;
			IBlockState iBlockState = Minecraft.getMinecraft().world.getBlockState(new BlockPos(n, n2, n3));
			if (iBlockState.getMaterial() == Material.AIR)
				continue;
			return true;
		}
		return false;
	}

	private boolean isOnBlock() {
		float f = 1.0f;
		for (float f2 = -f; f2 <= f; f2 += 1.0f) {
			int n = (int) Minecraft.getMinecraft().player.posX;
			int n2 = (int) (Minecraft.getMinecraft().player.posY - f2);
			int n3 = (int) Minecraft.getMinecraft().player.posZ;
			IBlockState iBlockState = Minecraft.getMinecraft().world.getBlockState(new BlockPos(n, n2, n3));
			if (iBlockState.getMaterial() == Material.AIR)
				continue;
			return true;
		}
		return false;
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && msTimer.checkMS()) {
			boolean bl = getBooleanValue("Down");
			float f = getFloatValue("Motion Y");
			double d = getFloatValue("Teleport Y");
			if (!bl) {
				float f2;
				if (isBelowBlock()) {
					mc.player.setPosition(mc.player.posX, mc.player.posY + d, mc.player.posZ);
					mc.getConnection().sendPacket(
							new CPacketPlayer.Position(mc.player.posX, mc.player.posY + d, mc.player.posZ, true));
				}
				mc.player.capabilities.isFlying = false;
				mc.player.motionX = 0.0;
				mc.player.motionY = 0.0;
				mc.player.motionZ = 0.0;
				mc.player.jumpMovementFactor = f2 = f;
				mc.player.motionY += f2;
			} else {
				if (isOnBlock()) {
					mc.player.setPosition(mc.player.posX, mc.player.posY - d, mc.player.posZ);
					mc.getConnection().sendPacket(
							new CPacketPlayer.Position(mc.player.posX, mc.player.posY - d, mc.player.posZ, true));
				}
				mc.player.capabilities.isFlying = false;
				mc.player.motionX = 0.0;
				mc.player.motionY = 0.0;
				mc.player.motionZ = 0.0;
				float f3 = f;
				mc.player.jumpMovementFactor = -f;
				mc.player.motionY -= f3;
			}
		}
	}
}
