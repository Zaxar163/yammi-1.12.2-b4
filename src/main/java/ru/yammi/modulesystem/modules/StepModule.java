package ru.yammi.modulesystem.modules;

import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class StepModule extends Module {

	public StepModule() {
		super("Step", ModuleCategory.MOVEMENT);
		getValues().add(new SliderValue("Height", 0.0f, 50.0f, 0.1f));
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		mc.player.stepHeight = getState() ? getFloatValue("Height") : 0.6f;
	}
}
