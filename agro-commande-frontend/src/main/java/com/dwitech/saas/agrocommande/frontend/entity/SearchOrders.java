package com.dwitech.saas.agrocommande.frontend.entity;

import org.jboss.resteasy.reactive.RestForm;

import static javax.json.bind.JsonbBuilder.create;

public class SearchOrders {
	@RestForm("msisdn") public String msisdn;
	@RestForm("code") public String code;
	@RestForm("status") public String status;

	@Override
	public String toString() {
		return create().toJson(this);
	}
}