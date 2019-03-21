package net.a.g.demo.byteman.stm;

import org.jboss.stm.annotations.ReadLock;
import org.jboss.stm.annotations.WriteLock;

public class AtomicInteger implements Atomic {
	@ReadLock
	public int get() throws Exception {
		return state;
	}

	@WriteLock
	public void set(int value) throws Exception {
		state = value;
	}

	@WriteLock
	public void add(int value) throws Exception {
		state += value;
	}

	private int state;
}