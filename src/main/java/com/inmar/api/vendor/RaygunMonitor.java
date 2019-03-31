package com.inmar.api.vendor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mindscapehq.raygun4java.core.RaygunClient;

@Component
public class RaygunMonitor {

	private static final Logger log = LogManager.getLogger(RaygunMonitor.class);

	@Value("${secret.raygunToken}")
	String raygunToken;

	RaygunClient client;

	public void send(Throwable throwable) {
		client = new RaygunClient(raygunToken);
		client.send(throwable);
	}
}
