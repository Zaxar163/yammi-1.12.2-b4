package ru.yammi.modulesystem;

import java.lang.reflect.Field;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.Render2DEvent;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.helpers.ConfigHelper;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.modulesystem.modules.XRayBlockModelRenderer;

public class ModuleController {

	private long keyTime = 0L;
	private Minecraft mc = Minecraft.getMinecraft();

	public int drawBlock(int n, int n3, int n4) {
		Item item = Item.getItemById(n);
		if (item != null)
			mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(item), n3, n4);
		if (keyTime <= System.currentTimeMillis() && Mouse.isButtonDown(0)) {
			int n5 = Mouse.getX() / 2;
			int n6 = mc.currentScreen.height - Mouse.getY() / 2;
			if (n5 >= n3 && n5 <= n3 + 16 && n6 >= n4 - 2 && n6 <= n4 + 16) {
				if (Mouse.isButtonDown(0)) {
					XRayBlockModelRenderer.xrayBlocks.removeIf(n2 -> n2 == n);
					ConfigHelper.store();
				}
				keyTime = System.currentTimeMillis() + 250L;
			}
		}
		return 16;
	}

	private boolean isKeyDown(int n) {
		if (Keyboard.isKeyDown(n) && keyTime <= System.currentTimeMillis()) {
			keyTime = System.currentTimeMillis() + 250L;
			return true;
		}
		return false;
	}

	@EventTarget
	public void onRender2D(Render2DEvent aFVmeSjveCmGfse) {
		if (mc.currentScreen != null && mc.currentScreen instanceof GuiChat) {
			ScaledResolution scaledResolution = new ScaledResolution(mc);
			int n = scaledResolution.getScaledWidth() - 16;
			int n2 = scaledResolution.getScaledHeight() - 32;
			int[] arrn = new int[XRayBlockModelRenderer.xrayBlocks.size()];
			for (int i = 0; i < arrn.length; ++i)
				arrn[i] = XRayBlockModelRenderer.xrayBlocks.get(i);
			for (int n3 : arrn) {
				n -= drawBlock(n3, n, n2);
				if (--n >= scaledResolution.getScaledWidth() * 0.6)
					continue;
				n2 -= 17;
				n = scaledResolution.getScaledWidth() - 6;
			}
		}
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		Field field;
		try {
			field = ReflectionHelper.getField(RenderGlobal.class, "damagedBlocks", "field_72738_E", "x");
			@SuppressWarnings("unchecked")
			Map<Integer, DestroyBlockProgress> map = (Map<Integer, DestroyBlockProgress>) field.get(mc.renderGlobal);
			if (map.isEmpty())
				map.put(0, new DestroyBlockProgress(mc.player.getEntityId(), new BlockPos(0, 0, 0)));
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		if (Minecraft.getMinecraft().currentScreen == null) {
			ModuleManager.getModules().stream().forEach(asaIBIYOhmSolWc -> {
				if (asaIBIYOhmSolWc.getKeybind() != 0 && isKeyDown(asaIBIYOhmSolWc.getKeybind())) {
					asaIBIYOhmSolWc.setState(!asaIBIYOhmSolWc.getState());
					boolean bl = asaIBIYOhmSolWc.getState();
					if (bl)
						asaIBIYOhmSolWc.onEnable();
					else
						asaIBIYOhmSolWc.onDisable();
				}
			});
			if (mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit != null
					&& mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK && isKeyDown(58)) {
				int n = Block.getStateId(mc.world.getBlockState(mc.objectMouseOver.getBlockPos()));
				if (!XRayBlockModelRenderer.xrayBlocks.contains(n))
					XRayBlockModelRenderer.xrayBlocks.add(n);
				else
					XRayBlockModelRenderer.xrayBlocks.removeIf(n2 -> n2 == n);
				ConfigHelper.store();
			}
		}
	}
}
