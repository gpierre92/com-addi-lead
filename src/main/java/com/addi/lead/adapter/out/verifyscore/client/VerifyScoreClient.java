package com.addi.lead.adapter.out.verifyscore.client;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "verifyScoreConfig", url = "${feign.client.config.verifyScoreConfig.base-url}")
public interface VerifyScoreClient {
    @GetMapping(value = "/verify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    int verifyById(@PathVariable String id);
}
