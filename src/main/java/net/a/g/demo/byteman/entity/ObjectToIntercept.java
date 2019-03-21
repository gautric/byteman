package net.a.g.demo.byteman.entity;

public class ObjectToIntercept {
	public String call() {
		return "Nominal Call";
	}
	
	@Deprecated
	public String callDeprecated() {
		return "Nominal callDeprecated";
	}
	
	public String getObject() {
		return "return getObject";
	}
}
