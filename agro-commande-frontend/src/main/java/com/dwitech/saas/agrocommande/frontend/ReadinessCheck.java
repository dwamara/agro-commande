package com.dwitech.saas.agrocommande.frontend;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

import static java.lang.String.format;
import static org.eclipse.microprofile.health.HealthCheckResponse.named;

@ApplicationScoped
@Readiness
public class ReadinessCheck implements HealthCheck {
	@ConfigProperty(name = "quarkus.application.version") String versionTag;
	@ConfigProperty(name = "quarkus.application.name") String applicationName;

	@Override
	public HealthCheckResponse call() {
		return named(format("%s (%s) : Readiness check", applicationName, versionTag)).up().build();
	}
}