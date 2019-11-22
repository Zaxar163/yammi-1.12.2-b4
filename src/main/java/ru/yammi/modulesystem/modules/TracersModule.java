package ru.yammi.modulesystem.modules;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import ru.yammi.eventsystem.EventTarget;
import ru.yammi.eventsystem.events.Render3DEvent;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;

public class TracersModule extends Module {

	public TracersModule() {
		super("Tracers", ModuleCategory.RENDER);
	}

	private void drawTracer(Entity entity, float f) {
		double d = mc.getRenderManager().viewerPosX;
		double d2 = mc.getRenderManager().viewerPosY;
		double d3 = mc.getRenderManager().viewerPosZ;
		double d4 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * f - d;
		double d5 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * f + entity.height / 2.0f - d2;
		double d6 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * f - d3;
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(3042);
		GL11.glEnable(2848);
		GL11.glDisable(2896);
		GL11.glLineWidth(1.0f);
		GL11.glDisable(3553);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		if (mc.player.getDistance(entity) >= 50.0f)
			GL11.glColor4d(0.1, 0.9, 0.1, 0.9);
		else if (mc.player.getDistance(entity) <= 50.0f && mc.player.getDistance(entity) >= 25.0f)
			GL11.glColor4d(0.9, 0.7, 0.1, 0.9);
		else if (mc.player.getDistance(entity) <= 25.0f && mc.player.getDistance(entity) >= 1.0f)
			GL11.glColor4d(0.9, 0.1, 0.1, 0.9);
		Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float) Math.toRadians(mc.player.rotationPitch))
				.rotateYaw(-(float) Math.toRadians(mc.player.rotationYaw));
		GL11.glBegin(1);
		GL11.glVertex3d(vec3d.x, mc.player.getEyeHeight() + vec3d.y, vec3d.z);
		GL11.glVertex3d(d4, d5, d6);
		GL11.glEnd();
		GL11.glEnable(3553);
		GL11.glEnable(2929);
		GL11.glEnable(2896);
		GL11.glDepthMask(true);
	}

	@EventTarget
	public void onRender3D(Render3DEvent ytROIMjqOZSKSkb2) {
		if (getState()) {
			mc.gameSettings.viewBobbing = false;
			mc.world.loadedEntityList.stream().forEach(entity -> {
				if (entity != null && entity != mc.player && entity instanceof EntityPlayer)
					try {
						drawTracer(entity, ReflectionHelper.getPartialTicks());
					} catch (Throwable throwable) {
						throwable.printStackTrace();
					}
			});
		} else
			mc.gameSettings.viewBobbing = true;
	}
}
