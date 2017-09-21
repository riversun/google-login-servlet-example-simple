package com.example.google_login;

import java.io.IOException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * 
 * Launcher class for OAuth2/OpenId connect servlet<br>
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 */
public class MyAppMain {

	public static void main(String[] args) throws IOException {
		startServer();
	}

	public static void startServer() {

		Server jettyServer = new Server(8080);

		ServletContextHandler ctx = new ServletContextHandler(ServletContextHandler.SESSIONS);

		// Add OAuth2 callback servlet.
		ctx.addServlet(new ServletHolder(new MyOAuthCallbackServlet()), "/callback");

		// Add filters for app servlet
		ctx.addFilter(MyOAuthFilter.class, "/app/*",
				EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

		// Add app servlet
		ctx.addServlet(new ServletHolder(new MyAppServlet()), "/app/main");

		jettyServer.setHandler(ctx);

		try {
			jettyServer.start();
			jettyServer.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
