package com.addi.lead.adapter.out.judicialrecord.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "JudicialRecordConfig", url = "${feign.client.config.judicialRecordConfig.base-url}")
public interface JudicialRecordClient {
    @GetMapping(value = "/verify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    Boolean verifyById(@PathVariable String id);
}
