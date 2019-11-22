package ru.yammi.modulesystem.modules;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class FastBridgeModule extends Module {

	public FastBridgeModule() {
		super("FastBridge", ModuleCategory.MISC);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState()) {
			boolean bl;
			BlockPos blockPos = new BlockPos(mc.player).add(0, -1, 0);
			bl = mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR;
			if (mc.gameSettings.keyBindJump.isKeyDown()) {
				if (!bl)
					setjump(false);
				setsneak(mc.player.onGround || bl);
				if (!mc.player.onGround) {
					mc.player.motionX = 0.0;
					mc.player.motionZ = 0.0;
				}
			} else
				setsneak(bl);
		}
	}

	public void setjump(boolean bl) {
		mc.player.movementInput.jump = bl;
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), bl);
	}

	public void setsneak(boolean bl) {
		mc.player.movementInput.sneak = bl;
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSneak.getKeyCode(), bl);
	}
}
