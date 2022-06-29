package com.dwitech.saas.agrocommande.frontend.entity;

import org.jboss.resteasy.reactive.RestForm;

import static javax.json.bind.JsonbBuilder.create;

public class ChangeStatus {
	@RestForm("code") public String code;
	@RestForm("action") public String action;
	@RestForm("msisdn") public String msisdn;

	@Override public String toString() {
		return create().toJson(this);
	}
}