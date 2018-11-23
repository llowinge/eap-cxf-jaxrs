package org.wildfly.camel.examples.cxf.jaxrs;

import java.util.concurrent.TimeUnit;

public class Timer {

	final long targetMillis;

	public Timer(final long till, final TimeUnit timeUnit) {
		targetMillis = System.currentTimeMillis() + timeUnit.toMillis(till);
	}

	public boolean elapsed() {
		return System.currentTimeMillis() > targetMillis;
	}
}
