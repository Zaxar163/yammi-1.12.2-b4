package ru.zaxar163.hacks;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import ru.yammi.modulesystem.ModuleCategory;

public class FreeItemHM extends PacketHackModule {
	public FreeItemHM() {
		super("FreeItemHM", ModuleCategory.MISC);
		setKeybind(Keyboard.KEY_L);
	}

	@Override
	public void prepare() throws Throwable {
		Class.forName("com.zeitheron.hammercore.net.internal.PacketDropItem");
	}

	@Override
	public void run() {
		ItemStack s = gainItemStack();
		if (s == null)
			return;
		try {
			EntityPlayerSP player = mc.player;

			EntityItem entityItem = new EntityItem(player.world, player.posX, player.posY, player.posZ, s);
			Object packet = Class.forName("com.zeitheron.hammercore.net.internal.PacketDropItem")
					.getConstructor(EntityItem.class).newInstance(entityItem);
			Object ncNet = Class.forName("com.zeitheron.hammercore.net.HCNet").getField("INSTANCE").get(null);
			ncNet.getClass().getDeclaredMethod("sendToServer", Class.forName("com.zeitheron.hammercore.net.IPacket"))
					.invoke(ncNet, packet);

		} catch (Exception e) {
		}
	}
}
