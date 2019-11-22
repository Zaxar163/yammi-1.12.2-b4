package ru.yammi.gui.elements;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventBus;
import ru.yammi.gui.ClickGUIScreen;
import ru.yammi.helpers.ConfigHelper;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleManager;

public class ModuleGuiElement {

	private boolean hovered = false;
	private boolean isSettingsOpened = false;
	private Minecraft mc = Minecraft.getMinecraft();
	private Module module;
	private long mouseTime = 0L;
	private int x = 0;
	private int y = 0;

	public ModuleGuiElement(Module asaIBIYOhmSolWc) {
		module = asaIBIYOhmSolWc;
	}

	private void checkMouseJoin() {
		if (mc != null && mc.currentScreen != null) {
			int n = Mouse.getEventX() * mc.currentScreen.width / mc.displayWidth;
			int n2 = mc.currentScreen.height - Mouse.getEventY() * mc.currentScreen.height / mc.displayHeight - 1;
			mouseJoin(n, n2);
		}
	}

	private void checkMouseJoinSlider(SliderValue dBUsaySotRtyDdU, int n) {
		int n2 = Mouse.getEventX() * mc.currentScreen.width / mc.displayWidth;
		int n3 = mc.currentScreen.height - Mouse.getEventY() * mc.currentScreen.height / mc.displayHeight - 1;
		mouseJoinSlider(dBUsaySotRtyDdU, n, n2, n3);
	}

	public void doRender(int n, int n2) {
		x = n;
		y = n2;
		checkMouseJoin();
		int n3 = new Color(128, 47, 161).getRGB();
		Gui.drawRect(x, y, x + 75, y + 20, new Color(37, 31, 40, 200).getRGB());
		int n4 = x + (75 - mc.fontRenderer.getStringWidth(module.getName())) / 2;
		if (module.getState()) {
			if (!hovered)
				mc.fontRenderer.drawString(module.getName(), n4, y + 6, -1);
			Module asaIBIYOhmSolWc2 = ModuleManager.getModule("ClickGUI");
			float f = asaIBIYOhmSolWc2.getFloatValue("Red Color");
			float f2 = asaIBIYOhmSolWc2.getFloatValue("Green Color");
			float f3 = asaIBIYOhmSolWc2.getFloatValue("Blue Color");
			float[] arrf = new float[] { f, f2, f3, 1.0f };
			if (f >= 0.1f || f2 >= 0.1f || f3 >= 0.1f)
				this.drawRect(x, y, x + 2, y + 20, arrf);
			else
				this.drawRect(x, y, x + 2, y + 20, n3);
		} else if (!hovered)
			mc.fontRenderer.drawString(module.getName(), n4, y + 6, -1);
		if (hovered) {
			Gui.drawRect(x, y, x + 75, y + 20, 587202559);
			mc.fontRenderer.drawString(module.getName(), n4, y + 6, Color.YELLOW.getRGB());
		}
		if (!getModule().getValues().isEmpty() || !getModule().getBooleanValues().isEmpty())
			mc.fontRenderer.drawString("+", x + 68, y + 7, Color.WHITE.getRGB());
		if (hovered) {
			if (Mouse.isButtonDown(0) && isMouseTimeReached()) {
				module.setState(!module.getState());
				boolean bl = module.getState();
				if (bl)
					module.onEnable();
				else
					module.onDisable();
				ConfigHelper.store();
				if (module.getName().equals("ClickGUI") && !module.getState())
					mc.displayGuiScreen(null);
				if (module.getName().equals("SelfDestruct")) {
					EventBus.clear();
					mc.displayGuiScreen(null);
				}
				if (module.getName().equals("XRay")) {
					// empty if block
				}
				if (module.getName().equals("Reset keybinds"))
					ModuleManager.getModules().stream().forEach(asaIBIYOhmSolWc -> {
						if (!asaIBIYOhmSolWc.getName().equals("ClickGUI"))
							asaIBIYOhmSolWc.setKeybind(0);
					});
			}
			if ((!getModule().getValues().isEmpty() || !getModule().getBooleanValues().isEmpty())
					&& Mouse.isButtonDown(1) && isMouseTimeReached())
				isSettingsOpened = !isSettingsOpened;
			if (Keyboard.getEventKey() != 0 && Keyboard.getEventKeyState() && mc.currentScreen != null
					&& mc.currentScreen instanceof ClickGUIScreen) {
				int n5 = Keyboard.getEventKey();
				if (n5 == 29 && !module.getName().equals("ClickGUI") && !module.getName().equals("Reset keybinds")) {
					module.setKeybind(0);
					ConfigHelper.store();
					return;
				}
				if (n5 != 54 && n5 != 58 && !module.getName().equals("ClickGUI")
						&& !module.getName().equals("Reset keybinds")) {
					module.setKeybind(n5);
					ConfigHelper.store();
				}
			}
		}
		if (isSettingsOpened) {
			int n6;
			int n7 = x + 80;
			int n8 = y;
			String string = module.getName();
			n4 = n7 + (75 - mc.fontRenderer.getStringWidth(string)) / 2;
			Module asaIBIYOhmSolWc3 = ModuleManager.getModule("ClickGUI");
			float f = asaIBIYOhmSolWc3.getFloatValue("Red Color");
			float f4 = asaIBIYOhmSolWc3.getFloatValue("Green Color");
			float f5 = asaIBIYOhmSolWc3.getFloatValue("Blue Color");
			float[] arrf = new float[] { f, f4, f5, 1.0f };
			if (f >= 0.1f || f4 >= 0.1f || f5 >= 0.1f)
				this.drawRect(n7, n8, n7 + 75, n8 + 11, arrf);
			else
				this.drawRect(n7, n8, n7 + 75, n8 + 11, n3);
			mc.fontRenderer.drawString(string, n4, y + 2, Color.WHITE.getRGB());
			n8 = y + 11;
			int n9 = 0;
			for (SliderValue object : getModule().getValues()) {
				Gui.drawRect(n7, n8 + 25 * n9, n7 + 75, n8 + 25 * n9 + 25, new Color(37, 31, 40, 200).getRGB());
				float f6 = object.getValue();
				String string2 = String.valueOf(new StringBuilder().append(object.getName()).append(": ").append(f6));
				Gui.drawRect(n7, n8 + 25 * n9 + 20, n7 + 75, n8 + 25 * n9 + 21, Color.WHITE.getRGB());
				n6 = object.getSliderX();
				if (n6 >= 72) {
					n6 = 72;
					object.setSliderX(n6);
				}
				if (f >= 0.1f || f4 >= 0.1f || f5 >= 0.1f)
					this.drawRect(n7 + object.getSliderX(), n8 + 25 * n9 + 15, n7 + 3 + object.getSliderX(),
							n8 + 25 * n9 + 25, arrf);
				else
					this.drawRect(n7 + object.getSliderX(), n8 + 25 * n9 + 15, n7 + 3 + object.getSliderX(),
							n8 + 25 * n9 + 25, n3);
				checkMouseJoinSlider(object, n8 + 25 * n9 + 15);
				if (object.isHovered() && Mouse.isButtonDown(0)) {
					float f7 = 0.0f;
					int n10 = object.getMouseX();
					if (n10 >= n7)
						object.setSliderX(n10 - n7);
					else
						object.setSliderX(n10 - (n7 + 75));
					f7 = object.getSliderX() / (72.0f / object.getMaxValue());
					if (f7 >= object.getMaxValue())
						f7 = object.getMaxValue();
					f7 = Math.abs(f7);
					String string3 = String.format("%.01f", f7);
					string3 = String.valueOf(new StringBuilder().append(string3.replace(",", ".")).append("F"));
					object.setValue(Float.parseFloat(string3));
					string2 = String.valueOf(new StringBuilder().append(object.getName()).append(": ")
							.append(String.format("%.01f", f7)));
					ConfigHelper.store();
				}
				mc.fontRenderer.drawString(string2, n7 + 2, n8 + 25 * n9 + 5, Color.WHITE.getRGB());
				++n9;
			}
			for (BooleanValue object : getModule().getBooleanValues()) {
				Gui.drawRect(n7, n8 + 25 * n9, n7 + 75, n8 + 25 * n9 + 25, new Color(37, 31, 40, 200).getRGB());
				mc.fontRenderer.drawString(object.getName(), n7 + 2, n8 + 25 * n9 + 8, Color.WHITE.getRGB());
				int n11 = n7 + 75;
				Gui.drawRect(n11 - 6, n8 + 25 * n9 + 6, n11 - 19, n8 + 25 * n9 + 19, Color.BLACK.getRGB());
				Gui.drawRect(n11 - 7, n8 + 25 * n9 + 7, n11 - 7 - 11, n8 + 25 * n9 + 18,
						new Color(37, 31, 40, 200).getRGB());
				if (isBoolBoxHovered(n11, n8 + 25 * n9 + 6)) {
					Gui.drawRect(n11 - 7, n8 + 25 * n9 + 7, n11 - 7 - 11, n8 + 25 * n9 + 18,
							new Color(87, 81, 90, 255).getRGB());
					if (Mouse.isButtonDown(0) && isMouseTimeReached()) {
						object.setState(!object.isState());
						ConfigHelper.store();
					}
				}
				if (object.isState()) {
					int n12 = n11 - 9;
					n6 = n8 + 25 * n9 + 10;
					drawLine(n12, n6, -4, 6);
					drawLine(n12 -= 4, n6 += 6, -2, -3);
					Gui.drawRect(mc.currentScreen.width + 1, mc.currentScreen.height + 1, mc.currentScreen.width + 2,
							mc.currentScreen.height + 2, Color.WHITE.getRGB());
				}
				++n9;
			}
		}
	}

	private void drawLine(int n, int n2, int n3, int n4) {
		GL11.glPushMatrix();
		Module asaIBIYOhmSolWc = ModuleManager.getModule("ClickGUI");
		float f = asaIBIYOhmSolWc.getFloatValue("Red Color");
		float f2 = asaIBIYOhmSolWc.getFloatValue("Green Color");
		float f3 = asaIBIYOhmSolWc.getFloatValue("Blue Color");
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glBlendFunc(770, 771);
		if (f >= 0.1f || f2 >= 0.1f || f3 >= 0.1f)
			GL11.glColor3f(f, f2, f3);
		else
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
		GL11.glDisable(2884);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glLineWidth(2.0f);
		GL11.glBegin(2);
		GL11.glVertex2i(n, n2);
		GL11.glVertex2i(n + n3, n2 + n4);
		GL11.glEnd();
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5f, 0.5f, 0.5f);
		GL11.glNormal3f(0.0f, 1.0f, 0.0f);
		GL11.glEnable(3553);
		GL11.glPopMatrix();
		GL11.glDepthMask(true);
		GL11.glEnable(2884);
		GL11.glEnable(3553);
		GL11.glEnable(2929);
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}

	public void drawRect(int n, int n2, int n3, int n4, float[] arrf) {
		int n5;
		if (n < n3) {
			n5 = n;
			n = n3;
			n3 = n5;
		}
		if (n2 < n4) {
			n5 = n2;
			n2 = n4;
			n4 = n5;
		}
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = tessellator.getBuffer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.color(arrf[0], arrf[1], arrf[2], arrf[3]);
		bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
		bufferBuilder.pos(n, n4, 0.0).endVertex();
		bufferBuilder.pos(n3, n4, 0.0).endVertex();
		bufferBuilder.pos(n3, n2, 0.0).endVertex();
		bufferBuilder.pos(n, n2, 0.0).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	public void drawRect(int n, int n2, int n3, int n4, int n5) {
		Gui.drawRect(n, n2, n3, n4, n5);
	}

	public Module getModule() {
		return module;
	}

	private boolean isBoolBoxHovered(int n, int n2) {
		int n3 = Mouse.getEventX() * mc.currentScreen.width / mc.displayWidth;
		int n4 = mc.currentScreen.height - Mouse.getEventY() * mc.currentScreen.height / mc.displayHeight - 1;
		int n5 = n - 19;
		int n6 = n2;
		int n7 = n - 6;
		int n8 = n2 + 12;
		return n3 >= n5 && n3 <= n7 && n4 >= n6 && n4 <= n8;
	}

	private boolean isMouseTimeReached() {
		if (mouseTime <= System.currentTimeMillis()) {
			mouseTime = System.currentTimeMillis() + 250L;
			return true;
		}
		return false;
	}

	public void mouseJoin(int n, int n2) {
		int n3 = x;
		int n4 = y;
		int n5 = x + 75;
		int n6 = y + 20;
		hovered = n >= n3 && n <= n5 && n2 >= n4 && n2 <= n6;
	}

	public void mouseJoinSlider(SliderValue dBUsaySotRtyDdU, int n, int n2, int n3) {
		int n4 = x;
		int n6 = n4 + 80;
		int n7 = n;
		int n8 = n6 + 75;
		int n9 = n + 10;
		if (n2 >= n6 && n2 <= n8 && n3 >= n7 && n3 <= n9) {
			dBUsaySotRtyDdU.setHovered(true);
			if (Mouse.isButtonDown(0))
				dBUsaySotRtyDdU.setMouseX(n2);
		} else
			dBUsaySotRtyDdU.setHovered(false);
	}
}
