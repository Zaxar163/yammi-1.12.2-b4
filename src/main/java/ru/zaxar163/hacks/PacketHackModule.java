package ru.zaxar163.hacks;

import net.minecraft.item.ItemStack;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public abstract class PacketHackModule extends Module implements Runnable {

	public static ItemStack ITEMSTACK = null;
	public final boolean workable;

	public PacketHackModule(String name, ModuleCategory cat) {
		super(name, cat);
		workable = tryPrepare();
	}

	public boolean tryPrepare() {
		try {
			prepare();
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

	public abstract void prepare() throws Throwable;

	@Override
	public void onEnable() {
		if (!getState())
			return;
		if (workable)
			hack();
		setState(!getState());
	}

	public void hack() {
		run();
	}

	public static ItemStack gainItemStack() {
		return ITEMSTACK;
	}
}