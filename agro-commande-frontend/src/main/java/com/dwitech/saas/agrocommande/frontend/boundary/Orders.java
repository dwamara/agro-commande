package com.dwitech.saas.agrocommande.frontend.boundary;

import com.dwitech.saas.agrocommande.frontend.control.GatewayResponse;
import com.dwitech.saas.agrocommande.frontend.entity.*;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestForm;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static com.dwitech.saas.agrocommande.frontend.control.EntryExitLogger.logEntry;
import static com.dwitech.saas.agrocommande.frontend.control.EntryExitLogger.logExit;
import static java.lang.StackWalker.getInstance;
import static java.lang.String.valueOf;
import static org.jboss.logging.Logger.getLogger;

public class Orders extends Controller {
	private static final Logger LOGGER = getLogger(Orders.class);
	private final MeterRegistry registry;
	@RestClient OrderClient client;
	@ConfigProperty(name = "quarkus.application.name") String applicationName;
	@ConfigProperty(name = "quarkus.profile") String profile;

	Orders(MeterRegistry registry) {
		this.registry = registry;
	}

	@CheckedTemplate
	static class Templates {
		public static native TemplateInstance place();
		public static native TemplateInstance retrieve();
		public static native TemplateInstance change_status();
		public static native TemplateInstance search();
	}


	@POST
	public void place(final CreateOrder request) {
		String action = (this.getClass().getSimpleName() + "." + getInstance().walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName)).get()).replace("_Subclass", "");
		logEntry(LOGGER, applicationName, profile, action, request);
		GatewayResponse response = client.place(request);

		if (response.success) {
			flash("message", "Order placed");
		}

		count(action, response);
		logExit(LOGGER, applicationName, profile, action, request, response.entity != null ? response.entity : response.errorMessage);
		Templates.place();
	}

	@Path("{order_code}")
	public void retrieve(@RestForm("order_code") final String orderCode) {
		String action = (this.getClass().getSimpleName() + "." + getInstance().walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName)).get()).replace("_Subclass", "");
		logEntry(LOGGER, applicationName, profile, action, orderCode);
		GatewayResponse response = client.retrieve(orderCode);
		count(action, response);
		logExit(LOGGER, applicationName, profile, action, orderCode, response.entity != null ? response.entity : response.errorMessage);
	}

	// validate, reject, delete
	@POST
	@Path("change-status")
	public void changeStatus(final ChangeStatus request) {
		String action = (this.getClass().getSimpleName() + "." + getInstance().walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName)).get()).replace("_Subclass", "");
		logEntry(LOGGER, applicationName, profile, action, request);
		GatewayResponse response = client.changeStatus(request);
		count(action, response);
		logExit(LOGGER, applicationName, profile, action, request, response.entity != null ? response.entity : response.errorMessage);
	}

	@POST
	@Path("search")
	public void search(final SearchOrders request) {
		String action = (this.getClass().getSimpleName() + "." + getInstance().walk(frames -> frames.findFirst().map(StackWalker.StackFrame::getMethodName)).get()).replace("_Subclass", "");
		logEntry(LOGGER, applicationName, profile, action, request);
		GatewayResponse response = client.search(request);
		count(action, response);
		logExit(LOGGER, applicationName, profile, action, request, response.entity != null ? response.entity : response.errorMessage);
	}

	private void count(String action, GatewayResponse response) {
		registry.counter(applicationName, "component", applicationName, "action", action, "success", valueOf(response.entity != null), "code", String.valueOf(response.httpCode)).increment();
	}
}