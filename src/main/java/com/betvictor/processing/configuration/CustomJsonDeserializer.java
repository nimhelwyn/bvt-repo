package com.betvictor.processing.configuration;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class CustomJsonDeserializer<T> extends JsonDeserializer<T> {
	public CustomJsonDeserializer() {
		super();
		this.addTrustedPackages("*");
	}
}
