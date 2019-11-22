package ru.yammi.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import ru.yammi.Main;
import ru.yammi.gui.elements.CategoryGuiElement;
import ru.yammi.helpers.particles.ParticleSystem;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.modulesystem.ModuleManager;
import ru.yammi.modulesystem.modules.ClickGUIModule;

public class ClickGUIScreen extends GuiScreen {

	private Minecraft mc = Minecraft.getMinecraft();
	private ParticleSystem particleSystem = new ParticleSystem(200);
	private List<CategoryGuiElement> tabs = new ArrayList<>();

	public ClickGUIScreen() {
		for (ModuleCategory xzSCDzduhxVFqcw : ModuleCategory.values())
			tabs.add(new CategoryGuiElement(xzSCDzduhxVFqcw));
		tabs.sort((kFwnqaLrgbrizPm2,
				kFwnqaLrgbrizPm3) -> mc.fontRenderer.getStringWidth(kFwnqaLrgbrizPm3.getCategory().name())
						- mc.fontRenderer.getStringWidth(kFwnqaLrgbrizPm2.getCategory().name()));
	}

	@Override
	public void drawScreen(int n, int n2, float f) {
		super.drawScreen(n, n2, f);
		doRender();
	}

	@Override
	public void mouseClicked(int n, int n2, int n3) {
		tabs.stream().forEach(kFwnqaLrgbrizPm2 -> kFwnqaLrgbrizPm2.mouseClicked(n, n2, n3));
	}

	@Override
	public void mouseClickMove(int n, int n2, int n3, long l) {
		tabs.stream().forEach(kFwnqaLrgbrizPm2 -> kFwnqaLrgbrizPm2.mouseClickMove(n, n2, n3, l));
	}

	@Override
	public void mouseReleased(int n, int n2, int n3) {
		tabs.stream().forEach(kFwnqaLrgbrizPm2 -> kFwnqaLrgbrizPm2.mouseReleased(n, n2, n3));
	}

	private void doRender() {
		int n;
		Gui.drawRect(0, 0, width, height, new Color(20, 20, 20, 200).getRGB());
		boolean bl = ModuleManager.getModule(ClickGUIModule.class).getBooleanValue("Particles");
		if (bl) {
			particleSystem.tick(10);
			particleSystem.render();
		}
		String string = (n = Main.getInstance().getDays()) >= 5 ? " \u0434\u043d\u0435\u0439" : " \u0434\u043d\u044f";
		mc.fontRenderer.drawString(String.valueOf(new StringBuilder().append(
				"\u041b\u0438\u0446\u0435\u043d\u0437\u0438\u044f \u0447\u0438\u0442\u0430 \u0438\u0441\u0442\u0435\u043a\u0430\u0435\u0442 \u0447\u0435\u0440\u0435\u0437 ")
				.append(n).append(string)), 2, height - mc.fontRenderer.FONT_HEIGHT - 14, -1);
		mc.fontRenderer.drawString(
				"\u041d\u0430\u0432\u0435\u0434\u0438\u0442\u0435\u0441\u044c \u043d\u0430 \u0431\u043b\u043e\u043a \u0438 \u043d\u0430\u0436\u043c\u0438\u0442\u0435 CAPS, \u0447\u0442\u043e\u0431\u044b \u0434\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u044d\u0442\u043e\u0442 \u0431\u043b\u043e\u043a \u0432 XRay",
				2, height - mc.fontRenderer.FONT_HEIGHT - 2, -1);
		tabs.stream().forEach(kFwnqaLrgbrizPm2 -> kFwnqaLrgbrizPm2.doRender());
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		if (ModuleManager.isModuleEnabled("ClickGUI"))
			ModuleManager.disableModule("ClickGUI");
	}
}
