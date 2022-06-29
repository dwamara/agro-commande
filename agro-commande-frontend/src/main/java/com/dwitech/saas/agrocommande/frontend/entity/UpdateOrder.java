package com.dwitech.saas.agrocommande.frontend.entity;

import org.jboss.resteasy.reactive.RestForm;

import static javax.json.bind.JsonbBuilder.create;

public class UpdateOrder {
	@RestForm("code") public String code;
	@RestForm("content") public String content;

	@Override
	public String toString() {
		return create().toJson(this);
	}
}