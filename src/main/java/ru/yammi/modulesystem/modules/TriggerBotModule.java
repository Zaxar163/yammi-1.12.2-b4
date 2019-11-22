package ru.yammi.modulesystem.modules;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class TriggerBotModule extends Module {

	public TriggerBotModule() {
		super("TriggerBot", ModuleCategory.COMBAT);
	}

	@EventTarget
	public void onUpdate(UpdateEvent event) {
		Entity entity;
		if (getState() && mc.objectMouseOver.typeOfHit != null
				&& mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY
				&& (entity = mc.objectMouseOver.entityHit) instanceof EntityPlayer && entity != mc.player) {
			EntityPlayer entityPlayer = (EntityPlayer) entity;
			mc.player.swingArm(EnumHand.MAIN_HAND);
			mc.player.connection.sendPacket(new CPacketUseEntity(entityPlayer));
		}
	}
}
