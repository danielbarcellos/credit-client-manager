package com.drbsoft.ccm.controller;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.drbsoft.ccm.utils.GsonUtils;

public class AbstractRestControllerTest {

	protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	protected MockMvc mockMvc;

	protected HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters)
				.stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}
	
	protected <T> String json(T o) throws IOException {
        return GsonUtils.GSON.toJson(o);
    }
	
	protected <T> T json(String json, Class<T> clazz) throws IOException {
        return GsonUtils.GSON.fromJson(json, clazz);
    }
}
