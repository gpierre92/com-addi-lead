package com.addi.lead.adapter.out.lead.client;

import com.addi.lead.adapter.out.lead.model.LeadResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "leadsConfig", url = "${feign.client.config.leadsConfig.base-url}")
public interface LeadsClient {
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers("Content-Type: application/json")
    LeadResponse getLeadById(@PathVariable String id);
}
