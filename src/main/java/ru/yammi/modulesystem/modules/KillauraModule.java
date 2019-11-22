package ru.yammi.modulesystem.modules;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class KillauraModule extends Module {

	private float ticks = 0.0f;

	public KillauraModule() {
		super("Killaura", ModuleCategory.COMBAT);
		getValues().add(new SliderValue("Radius", 0.0f, 6.5f, 0.1f));
		getValues().add(new SliderValue("Delay", 0.0f, 20.0f, 1.0f));
		getBooleanValues().add(new BooleanValue("Mobs"));
	}

	private List<Entity> collectEntities() {
		ArrayList<Entity> arrayList = new ArrayList<>();
		mc.world.loadedEntityList.forEach(entity -> {
			float f = getFloatValue("Radius");
			if (entity != null && entity != mc.player) {
				if (entity instanceof EntityPlayer && mc.player.getDistance(entity) <= f)
					arrayList.add(entity);
				if (getBooleanValue("Mobs") && entity instanceof EntityLivingBase && mc.player.getDistance(entity) <= f)
					arrayList.add(entity);
			}
		});
		return arrayList;
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState()) {
			ticks += 1.0f;
			float f = getFloatValue("Delay");
			if (ticks >= f) {
				collectEntities().stream().forEach(entity -> {
					mc.player.swingArm(EnumHand.MAIN_HAND);
					mc.getConnection().sendPacket(new CPacketUseEntity(entity));
				});
				ticks = 0.0f;
			}
		}
	}
}
