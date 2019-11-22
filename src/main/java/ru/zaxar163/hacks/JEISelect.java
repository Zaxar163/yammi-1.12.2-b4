package ru.zaxar163.hacks;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.ModuleCategory;

public class JEISelect extends PacketHackModule {

	public JEISelect() {
		super("JEISelect", ModuleCategory.MISC);
		super.setKeybind(Keyboard.KEY_TAB);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (!super.workable || !getState() || mc.currentScreen == null || !(mc.currentScreen instanceof GuiContainer))
			return;
		if (Keyboard.isKeyDown(super.getKeybind()))
			run();
	}

	@Override
	public void run() {
		try {
			GuiContainer container = mc.currentScreen instanceof GuiContainer ? (GuiContainer) mc.currentScreen : null;
			if (container == null)
				return;
			Object runtime = Class.forName("mezz.jei.Internal").getDeclaredMethod("getRuntime").invoke(null);
			Object itemList = runtime.getClass().getDeclaredMethod("getItemListOverlay").invoke(runtime);
			Object item = itemList.getClass().getDeclaredMethod("getStackUnderMouse").invoke(itemList);
			if (item instanceof ItemStack) {
				ItemStack itemStack = (ItemStack) item;
				int count = GuiScreen.isShiftKeyDown() ? itemStack.getMaxStackSize() : 1;
				itemStack = itemStack.copy();
				itemStack.setCount(count);
				PacketHackModule.ITEMSTACK = itemStack;
			}
		} catch (Exception ignored) {
		}
	}

	@Override
	public void prepare() throws Throwable {
		Class.forName("mezz.jei.Internal");
	}
}
