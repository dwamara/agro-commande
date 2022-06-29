package com.dwitech.saas.agrocommande.frontend.boundary;

import com.dwitech.saas.agrocommande.frontend.control.GatewayResponse;
import com.dwitech.saas.agrocommande.frontend.entity.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static javax.ws.rs.core.MediaType.MULTIPART_FORM_DATA;

@Path("/orders")
@RegisterRestClient
public interface OrderClient {
	@POST
	@Consumes(MULTIPART_FORM_DATA)
	GatewayResponse place(final CreateOrder request);

	@GET
	@Path("{order_code}")
	GatewayResponse retrieve(@RestForm("order_code") final String orderCode);

	// validate, reject, delete
	@POST
	@Path("change-status")
	@Consumes(MULTIPART_FORM_DATA)
	GatewayResponse changeStatus(final ChangeStatus request);

	@POST
	@Path("search")
	@Consumes(MULTIPART_FORM_DATA)
	GatewayResponse search(final SearchOrders request);
}
