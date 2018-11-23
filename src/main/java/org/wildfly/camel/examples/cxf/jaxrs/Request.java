package org.wildfly.camel.examples.cxf.jaxrs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {
	private String text;
	private long delay;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public long getDelay() {
		return delay;
	}
}
