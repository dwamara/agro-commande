package com.dwitech.saas.agrocommande.frontend.control;

import org.jboss.logging.Logger;

import static java.lang.String.format;


public class EntryExitLogger {

	public static void logEntry(final Logger LOGGER, final String applicationName, final String profile, final String action) {
		logEntry(LOGGER, applicationName, profile, action, null);
	}

	public static void logEntry(final Logger LOGGER, final String applicationName, final String profile, final String action, final Object data) {
		LOGGER.info(format("[%s_%s] %s with data '%s'", applicationName, profile, action, data == null ? "" : data));
	}

	public static void logExit(final Logger LOGGER, final String applicationName, final String profile, final String action, final Object param) {
		logExit(LOGGER, applicationName, profile, action, param, null);
	}

	public static void logExit(final Logger LOGGER, final String applicationName, final String profile, final String action, final Object param, final Object result) {
		LOGGER.info(format("[%s_%s] %s  with data '%s' : %s", applicationName, profile, action, param == null ? "" : param, result == null ? "" : result));
	}
}
