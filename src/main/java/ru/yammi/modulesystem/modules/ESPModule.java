package ru.yammi.modulesystem.modules;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class ESPModule extends Module {

	public ESPModule() {
		super("ESP", ModuleCategory.RENDER);
		getBooleanValues().add(new BooleanValue("Players"));
		getBooleanValues().add(new BooleanValue("Mobs"));
		getValues().add(new SliderValue("Red Color", 0.0f, 1.0f, 0.1f));
		getValues().add(new SliderValue("Green Color", 0.0f, 1.0f, 0.1f));
		getValues().add(new SliderValue("Blue Color", 0.0f, 1.0f, 0.1f));
		getValues().add(new SliderValue("Line Width", 0.0f, 5.0f, 0.1f));
	}

	private void doRenderEntity(Entity entity) {
		try {
			GL11.glBlendFunc(770, 771);
			GL11.glEnable(3042);
			GL11.glEnable(2848);
			GL11.glDisable(2896);
			float f = getFloatValue("Line Width");
			if (f >= 0.1f)
				GL11.glLineWidth(f);
			else
				GL11.glLineWidth(1.0f);
			GL11.glDisable(3553);
			GL11.glDisable(2929);
			GL11.glDepthMask(false);
			float f2 = getFloatValue("Red Color");
			float f3 = getFloatValue("Green Color");
			float f4 = getFloatValue("Blue Color");
			if (f2 >= 0.1f || f3 >= 0.1f || f4 >= 0.1f)
				GL11.glColor3f(f2, f3, f4);
			else
				GL11.glColor3f(1.0f, 0.0f, 0.0f);
			ReflectionHelper.getRenderPosX();
			ReflectionHelper.getRenderPosY();
			ReflectionHelper.getRenderPosZ();
			double d4 = entity.posX - ReflectionHelper.getRenderPosX() - 0.5;
			double d5 = entity.posY - ReflectionHelper.getRenderPosY();
			double d6 = entity.posZ - ReflectionHelper.getRenderPosZ() - 0.5;
			double d7 = 0.0;
			double d8 = 0.0;
			double d9 = 0.0;
			GL11.glTranslated(d4, d5, d6);
			GL11.glBegin(1);
			GL11.glVertex3d(d7, d8, d9);
			GL11.glVertex3d(d7, d8 + 2.0, d9);
			GL11.glVertex3d(d7, d8 + 2.0, d9);
			GL11.glVertex3d(d7 + 1.0, d8 + 2.0, d9);
			GL11.glVertex3d(d7 + 1.0, d8 + 2.0, d9);
			GL11.glVertex3d(d7 + 1.0, d8, d9);
			GL11.glVertex3d(d7 + 1.0, d8, d9);
			GL11.glVertex3d(d7, d8, d9);
			GL11.glVertex3d(d7 + 1.0, d8, d9);
			GL11.glVertex3d(d7 + 1.0, d8 + 2.0, d9);
			GL11.glVertex3d(d7 + 1.0, d8 + 2.0, d9);
			GL11.glVertex3d(d7 + 1.0, d8 + 2.0, d9 + 1.0);
			GL11.glVertex3d(d7 + 1.0, d8 + 2.0, d9 + 1.0);
			GL11.glVertex3d(d7 + 1.0, d8, d9 + 1.0);
			GL11.glVertex3d(d7 + 1.0, d8, d9 + 1.0);
			GL11.glVertex3d(d7 + 1.0, d8, d9);
			GL11.glVertex3d(d7 + 1.0, d8, d9 + 1.0);
			GL11.glVertex3d(d7 + 1.0, d8 + 2.0, d9 + 1.0);
			GL11.glVertex3d(d7 + 1.0, d8 + 2.0, d9 + 1.0);
			GL11.glVertex3d(d7, d8 + 2.0, d9 + 1.0);
			GL11.glVertex3d(d7, d8 + 2.0, d9 + 1.0);
			GL11.glVertex3d(d7, d8, d9 + 1.0);
			GL11.glVertex3d(d7, d8, d9 + 1.0);
			GL11.glVertex3d(d7 + 1.0, d8, d9 + 1.0);
			GL11.glVertex3d(d7, d8, d9 + 1.0);
			GL11.glVertex3d(d7, d8 + 2.0, d9 + 1.0);
			GL11.glVertex3d(d7, d8 + 2.0, d9 + 1.0);
			GL11.glVertex3d(d7, d8 + 2.0, d9);
			GL11.glVertex3d(d7, d8 + 2.0, d9);
			GL11.glVertex3d(d7, d8, d9);
			GL11.glVertex3d(d7, d8, d9);
			GL11.glVertex3d(d7, d8, d9 + 1.0);
			GL11.glEnd();
			GL11.glTranslated(-d4, -d5, -d6);
			GL11.glEnable(3553);
			GL11.glEnable(2929);
			GL11.glEnable(2896);
			GL11.glDepthMask(true);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	@EventTarget
	public void onRender3D(Render3DEvent ytROIMjqOZSKSkb2) {
		if (getState())
			mc.world.loadedEntityList.stream().forEach(entity -> {
				if (entity != null && entity != mc.player) {
					boolean bl = getBooleanValue("Players");
					boolean bl2 = getBooleanValue("Mobs");
					if (bl && entity instanceof EntityPlayer)
						doRenderEntity(entity);
					if (bl2 && entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer))
						doRenderEntity(entity);
				}
			});
	}
}
