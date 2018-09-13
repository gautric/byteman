package net.a.g.demo.byteman;

import org.jboss.stm.annotations.Transactional;

@Transactional
public interface Atomic {
	public void change(int value) throws Exception;

	public void set(int value) throws Exception;

	public int get() throws Exception;
}