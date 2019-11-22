package ru.yammi.modulesystem.modules;

import java.lang.reflect.Field;

import net.minecraft.entity.Entity;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class NoWebModule extends Module {

	public NoWebModule() {
		super("NoWeb", ModuleCategory.MOVEMENT);
	}

	@EventTarget
	public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
		if (getState())
			try {
				Field field = ReflectionHelper.getField(Entity.class, "isInWeb", "field_70134_J", "E");
				field.setBoolean(mc.player, false);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
	}
}
