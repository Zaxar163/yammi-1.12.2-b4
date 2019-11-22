package ru.yammi.helpers.particles;

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

public class Particle {

	private float alpha;
	private Vector2f pos;
	private static final Random random = new Random();
	private float size;
	private Vector2f velocity;

	public Particle(Vector2f vector2f, float f, float f2, float f3) {
		velocity = vector2f;
		pos = new Vector2f(f, f2);
		size = f3;
	}

	public double distance(float f, float f2, float f3, float f4) {
		return Math.sqrt((f - f3) * (f - f3) + (f2 - f4) * (f2 - f4));
	}

	public static Particle generateParticle() {
		Vector2f vector2f = new Vector2f((float) (Math.random() * 2.0 - 1.0), (float) (Math.random() * 2.0 - 1.0));
		float f = random.nextInt(Display.getWidth());
		float f2 = random.nextInt(Display.getHeight());
		float f3 = (float) (Math.random() * 4.0) + 1.0f;
		return new Particle(vector2f, f, f2, f3);
	}

	public float getAlpha() {
		return alpha;
	}

	public float getDistanceTo(Particle jEMXLMISzubihMw2) {
		return this.getDistanceTo(jEMXLMISzubihMw2.getX(), jEMXLMISzubihMw2.getY());
	}

	public float getDistanceTo(float f, float f2) {
		return (float) distance(getX(), getY(), f, f2);
	}

	public float getSize() {
		return size;
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public float getX() {
		return pos.getX();
	}

	public float getY() {
		return pos.getY();
	}

	public void setSize(float f) {
		size = f;
	}

	public void setVelocity(Vector2f vector2f) {
		velocity = vector2f;
	}

	public void setX(float f) {
		pos.setX(f);
	}

	public void setY(float f) {
		pos.setY(f);
	}

	public void tick(int n, float f) {
		pos.x += velocity.getX() * n * f;
		pos.y += velocity.getY() * n * f;
		if (alpha < 255.0f)
			alpha += 0.05f * n;
		if (pos.getX() > Display.getWidth())
			pos.setX(0.0f);
		if (pos.getX() < 0.0f)
			pos.setX(Display.getWidth());
		if (pos.getY() > Display.getHeight())
			pos.setY(0.0f);
		if (pos.getY() < 0.0f)
			pos.setY(Display.getHeight());
	}
}
