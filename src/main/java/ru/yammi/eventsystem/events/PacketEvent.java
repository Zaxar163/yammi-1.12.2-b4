package ru.yammi.eventsystem.events;

import net.minecraft.network.Packet;
import ru.yammi.eventsystem.Event;

public class PacketEvent extends Event {

	private Packet<?> packet;

	public PacketEvent(Packet<?> packet) {
		this.packet = packet;
	}

	public Packet<?> getPacket() {
		return packet;
	}
}
