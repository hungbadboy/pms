package com.tvo.pms.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@WebListener
public class TrackerHttpSessionListenner implements HttpSessionListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(TrackerHttpSessionListenner.class);
	private static int totalActiveSessions;
	  
	@Override
	public void sessionCreated(HttpSessionEvent sessionCount) {
		LOGGER.warn("Increment the session count" + totalActiveSessions);
		totalActiveSessions++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		totalActiveSessions--;
		LOGGER.warn("Current session count " + totalActiveSessions);
	}

}
