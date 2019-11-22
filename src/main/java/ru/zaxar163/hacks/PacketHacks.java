package ru.zaxar163.hacks;

import ru.yammi.modulesystem.ModuleManager;

public final class PacketHacks {

	private PacketHacks() {
	}

	public static void register() {
		ModuleManager.registerModule(new JEISelect());
		ModuleManager.registerModule(new FreeItemBD());
		ModuleManager.registerModule(new FreeItemHM());
		ModuleManager.registerModule(new ErrorFlood());
	}
}
