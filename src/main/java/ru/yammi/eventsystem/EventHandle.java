package ru.yammi.eventsystem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

final class EventHandle {

	private Object handle;
	private List<Method> methods = new ArrayList<>();

	public EventHandle(Object object, List<Method> list) {
		handle = object;
		methods = list;
	}

	public Object getHandle() {
		return handle;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setHandle(Object object) {
		handle = object;
	}

	public void setMethods(List<Method> list) {
		methods = list;
	}
}
