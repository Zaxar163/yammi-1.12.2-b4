package ru.yammi.modulesystem.modules;

import net.minecraft.network.play.client.CPacketPlayer;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class NoFallModule extends Module {

	public NoFallModule() {
		super("NoFall", ModuleCategory.MOVEMENT);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.player.fallDistance > 2.0f)
			mc.player.connection.sendPacket(new CPacketPlayer(true));
	}
}
