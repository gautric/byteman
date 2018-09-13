package net.a.g.demo.byteman;

import org.jboss.stm.annotations.ReadLock;
import org.jboss.stm.annotations.WriteLock;

public class ExampleSTM implements Atomic {
	@ReadLock
	public int get() throws Exception {
		return state;
	}

	@WriteLock
	public void set(int value) throws Exception {
		state = value;
	}

	@WriteLock
	public void change(int value) throws Exception {
		state += value;
	}

	private int state;
}