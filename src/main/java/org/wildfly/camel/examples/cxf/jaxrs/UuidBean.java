package org.wildfly.camel.examples.cxf.jaxrs;

import java.util.UUID;

public class UuidBean {

	private static final String _uuid = UUID.randomUUID().toString();

	public static String uuid() {
		return _uuid;
	}
}
