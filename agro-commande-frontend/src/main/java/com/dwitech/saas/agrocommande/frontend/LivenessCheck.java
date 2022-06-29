package com.dwitech.saas.agrocommande.frontend;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.eclipse.microprofile.health.HealthCheckResponse.named;

@ApplicationScoped
@Liveness
public class LivenessCheck implements HealthCheck {
	@ConfigProperty(name = "quarkus.application.version") String versionTag;
	@ConfigProperty(name = "quarkus.application.name") String applicationName;

	@Override
	public HealthCheckResponse call() {
		return named(format("%s (%s) Reachable : %s", applicationName, versionTag, new Date(currentTimeMillis()))).up().build();
	}
}