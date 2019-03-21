/*
 * JBoss, Home of Professional Open Source
 * Copyright 2006, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package net.a.g.demo.byteman.stm;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.jboss.byteman.contrib.bmunit.BMScript;
import org.jboss.stm.Container;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arjuna.ats.arjuna.AtomicAction;

import net.a.g.demo.byteman.stm.Atomic;
import net.a.g.demo.byteman.stm.AtomicInteger;

/**
 * based on @author Mark Little
 */
@RunWith(org.jboss.byteman.contrib.bmunit.BMUnitRunner.class)
public class BasicIntUnitTest {

	@Test
	public void testExample() throws Exception {
		Container<Atomic> theContainer = new Container<Atomic>();
		AtomicInteger basic = new AtomicInteger();
		Atomic obj = null;

		obj = theContainer.create(basic);

		AtomicAction a = new AtomicAction();

		obj.set(4321);

		a.begin();

		obj.set(1234);

		a.commit();

		assertThat(obj.get(), is(1234));

		a = new AtomicAction();

		a.begin();

		obj.add(1);

		assertThat(obj.get(), is(1235));

		a.abort();

		assertThat(obj.get(), is(1234));
	}

	@Test
	@BMScript(value = "throw.btm", dir = "src/test/resources")
	public void testExampleThrow() throws Exception {
		Container<Atomic> theContainer = new Container<Atomic>();
		AtomicInteger basic = new AtomicInteger();
		Atomic obj = null;

		obj = theContainer.create(basic);

		AtomicAction a = new AtomicAction();

		obj.set(4321);

		a.begin();

		obj.set(1234);

		a.commit();

		assertThat(obj.get(), is(1234));

		a = new AtomicAction();
		try {
			a.begin();

			obj.add(1);

			fail("Exception must be thrown by Byteman");

			a.commit();

		} catch (Exception e) {
			a.abort();
		}

		assertThat(obj.get(), is(1234));

	}
}
