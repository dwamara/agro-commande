package com.dwitech.saas.agrocommande.frontend.control;

import javax.ws.rs.core.Response.Status;

public class GatewayException extends Exception {
	public String internalCode;
	public Status httpStatus;

	public GatewayException(final String internalCode, final String message) {
		super(message);
		this.internalCode = internalCode;
	}

	public GatewayException(Status httpStatus, String internalCode, String message) {
		super(message);
		this.internalCode = internalCode;
		this.httpStatus = httpStatus;
	}
}