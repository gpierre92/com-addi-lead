package com.addi.lead.adapter.out.nationalregistry.client;

import com.addi.lead.adapter.out.nationalregistry.model.NationalRegistryResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "NationalRegistryConfig", url = "${feign.client.config.nationalRegistryConfig.base-url}")
public interface NationalRegistryClient {
    @GetMapping(value = "/verify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    NationalRegistryResponse verifyById(@PathVariable String id);
}
