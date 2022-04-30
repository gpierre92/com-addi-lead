package com.addi.lead.adapter.in.controller;

import com.addi.lead.adapter.in.controller.model.EvaluateRequest;
import com.addi.lead.domain.port.in.LeadInteractor;
import io.reactivex.Completable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class LeadRestController
 * Controller that expose api
 *
 * @author gpalacios
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class LeadRestController {

    private LeadInteractor leadInteractor;

    public LeadRestController(LeadInteractor leadInteractor) {
        this.leadInteractor = leadInteractor;
    }

    /**
     * doEvaluate method that allows evaluate lead
     *
     * @param evaluateRequest EvaluateRequest
     * @return Completable
     */
    @PostMapping(value = "/evaluate", produces = MediaType.APPLICATION_JSON_VALUE)
    public Completable doEvaluate(@Validated @RequestBody EvaluateRequest evaluateRequest) {
        return leadInteractor.doEvaluate(evaluateRequest.getIdentityNumber())
                .doOnError(ex -> log.error("Error in doEvaluate " + ex));
    }
}
