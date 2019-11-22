package ru.yammi.modulesystem.modules;

import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;
import ru.yammi.gui.ClickGUIScreen;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class ClickGUIModule extends Module {

	private ClickGUIScreen clickGUIScreen;

	public ClickGUIModule() {
		super("ClickGUI", ModuleCategory.MISC);
		setKeybind(54);
		getValues().add(new SliderValue("Red Color", 0.0f, 1.0f, 0.1f));
		getValues().add(new SliderValue("Green Color", 0.0f, 1.0f, 0.1f));
		getValues().add(new SliderValue("Blue Color", 0.0f, 1.0f, 0.1f));
		getBooleanValues().add(new BooleanValue("Particles"));
	}

	public ClickGUIScreen getClickGUIScreen() {
		return clickGUIScreen;
	}

	@Override
	public void onDisable() {
		if (mc.currentScreen != null && mc.currentScreen instanceof ClickGUIScreen)
			mc.displayGuiScreen(null);
	}

	@Override
	public void onEnable() {
		mc.displayGuiScreen(getClickGUIScreen());
	}

	public void setClickGUIScreen(ClickGUIScreen nqCPORZikVPseOK) {
		clickGUIScreen = nqCPORZikVPseOK;
	}
}
