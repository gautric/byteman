package net.a.g.demo.byteman.entity;

import static org.junit.Assert.assertEquals;

import org.jboss.byteman.contrib.bmunit.BMScript;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.a.g.demo.byteman.entity.ObjectToIntercept;

@RunWith(org.jboss.byteman.contrib.bmunit.BMUnitRunner.class)
public class ObjectToInterceptTest {

	@Test
	public void testNominalCall() {
		ObjectToIntercept oti = new ObjectToIntercept();
		assertEquals("Nominal Call", oti.call());
	}

	@Test
	@BMScript(value = "return.btm", dir = "src/test/resources")
	public void testBytemanedCall() {
		ObjectToIntercept oti = new ObjectToIntercept();
		assertEquals("### ByteMan Call ###", oti.call());
	}

	@Test
	public void testBytemanedCallDeprecated() {
		ObjectToIntercept oti = new ObjectToIntercept();
		assertEquals("### AspectJ Call ###", oti.callDeprecated());
	}

}
