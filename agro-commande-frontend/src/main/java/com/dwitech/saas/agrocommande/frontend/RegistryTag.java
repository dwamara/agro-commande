package com.dwitech.saas.agrocommande.frontend;

import io.micrometer.core.instrument.config.MeterFilter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.util.List;

import static io.micrometer.core.instrument.Tag.of;
import static io.micrometer.core.instrument.config.MeterFilter.commonTags;

@Singleton
public class RegistryTag {
	@ConfigProperty(name = "quarkus.profile") String profile;
	@ConfigProperty(name = "quarkus.application.name") String applicationName;

	@Produces
	@Singleton
	public MeterFilter configureAllRegistries() {
		return commonTags(List.of(
				of("profile", profile),
				of("application", applicationName)
				));
	}
}