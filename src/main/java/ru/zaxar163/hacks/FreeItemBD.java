package ru.zaxar163.hacks;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import ru.yammi.modulesystem.ModuleCategory;

public class FreeItemBD extends PacketHackModule {
	private Object instance;
	private Method senToServer;
	private Constructor<?> packetConstructor;

	public FreeItemBD() {
		super("FreeItemBD", ModuleCategory.MISC);
		setKeybind(Keyboard.KEY_P);
	}

	@Override
	public void run() {
		ItemStack s = gainItemStack();
		if (s == null)
			return;
		try {
			BlockPos blockPos = mc.objectMouseOver.getBlockPos();
			TileEntity entity = mc.world.getTileEntity(blockPos);
			if (entity != null && entity.getClass().equals(Class.forName("betterquesting.blocks.TileSubmitStation"))) {
				NonNullList<ItemStack> itemList = NonNullList.create();
				itemList.add(s);
				NBTTagCompound payload = new NBTTagCompound();
				payload.setInteger("x", blockPos.getX());
				payload.setInteger("y", blockPos.getY());
				payload.setInteger("z", blockPos.getZ());
				payload.setTag("inventory", ItemStackHelper.saveAllItems(new NBTTagCompound(), itemList));
				payload.setString("owner", Minecraft.getMinecraft().player.getUniqueID().toString());
				payload.setInteger("questID", -1);
				payload.setInteger("task", -1);

				NBTTagCompound tile = new NBTTagCompound();
				tile.setTag("tile", payload);

				Object packet = packetConstructor.newInstance(new ResourceLocation("betterquesting:edit_station"),
						tile);
				senToServer.invoke(instance, packet);
			}
		} catch (Exception ignored) {
		}
	}

	@Override
	public void prepare() throws Throwable {
		instance = Class.forName("betterquesting.network.PacketSender").getField("INSTANCE").get(null);
		senToServer = instance.getClass().getMethod("sendToServer",
				Class.forName("betterquesting.api.network.QuestingPacket"));
		packetConstructor = Class.forName("betterquesting.api.network.QuestingPacket")
				.getConstructor(ResourceLocation.class, NBTTagCompound.class);
	}
}
