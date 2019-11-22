package ru.yammi.modulesystem.modules;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class FastBreakModule extends Module {

	public FastBreakModule() {
		super("FastBreak", ModuleCategory.MISC);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK)
			try {
				BlockPos blockPos = mc.objectMouseOver.getBlockPos();
				float f = ReflectionHelper.getField(PlayerControllerMP.class, "curBlockDamageMP", "field_78770_f", "e")
						.getFloat(mc.playerController);
				float f2 = mc.world.getBlockState(blockPos).getPlayerRelativeBlockHardness(mc.player, mc.world,
						blockPos);
				float f3 = f + f2;
				if (f3 >= 1.0f)
					return;
				ReflectionHelper.getField(PlayerControllerMP.class, "blockHitDelay", "field_78781_i", "g")
						.setInt(mc.playerController, 0);
				ReflectionHelper.getField(PlayerControllerMP.class, "curBlockDamageMP", "field_78770_f", "e")
						.setFloat(mc.playerController, f3);
				mc.getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK,
						blockPos, mc.objectMouseOver.sideHit));
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
	}
}
