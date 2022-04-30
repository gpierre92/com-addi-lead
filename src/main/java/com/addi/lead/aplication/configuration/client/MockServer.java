package com.addi.lead.aplication.configuration.client;

import com.addi.lead.adapter.out.lead.model.LeadResponse;
import com.addi.lead.adapter.out.nationalregistry.model.NationalRegistryResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Class MockServer
 * Simulate interaction with external APIS
 *
 * @author gpalacios
 */
@Slf4j
@Configuration
public class MockServer {

    private static final int PORT = 8080;
    private static final String HEADER_JSON = "application/json";
    private WireMockServer wireMockServer;

    public MockServer() {
        wireMockServer = new WireMockServer(PORT);
        wireMockServer.start();
        this.mockSuccess();
        this.mockOnSuccessWithDelay();
        this.mockErrorOnScore();
        this.mockOnErrorWithDelayInLeadApi();
    }

    /**
     * Simulate interaction with external APIS with success state,
     * For success state use Id: 12345678
     */
    public void mockSuccess() {
        String identityNumber = "12345678";
        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/lead/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(
                                LeadResponse.builder()
                                        .identityNumber(identityNumber)
                                        .firstName("firstName001")
                                        .lastName("lastname001")
                                        .email("firstName@mail.com")
                                        .build()
                        ))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/judicial/verify/" + identityNumber))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(true))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/national/verify/" + identityNumber))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(
                                NationalRegistryResponse.builder()
                                        .firstName("firstName001")
                                        .lastName("lastname001")
                                        .email("firstName@mail.com")
                                        .build()
                        ))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/score/verify/" + identityNumber))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(65))));
    }

    /**
     * Simulate interaction with external APIS with success state and delay,
     * For success state use Id: 12345679
     */
    public void mockOnSuccessWithDelay() {
        String identityNumber = "12345679";
        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/lead/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(2000)
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(
                                LeadResponse.builder()
                                        .identityNumber(identityNumber)
                                        .firstName("firstName001")
                                        .lastName("lastname001")
                                        .email("firstName@mail.com")
                                        .build()
                        ))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/judicial/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(2000)
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(true))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/national/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(2000)
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(
                                NationalRegistryResponse.builder()
                                        .firstName("firstName001")
                                        .lastName("lastname001")
                                        .email("firstName@mail.com")
                                        .build()
                        ))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/score/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(2000)
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(65))));
    }

    /**
     * Simulate interaction with external APIS with error in score,
     * For error state with score lower than 60 use Id: 12345680
     */
    public void mockErrorOnScore() {
        String identityNumber = "12345680";
        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/lead/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(
                                LeadResponse.builder()
                                        .identityNumber(identityNumber)
                                        .firstName("firstName001")
                                        .lastName("lastname001")
                                        .email("firstName@mail.com")
                                        .build()
                        ))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/judicial/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(true))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/national/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(
                                NationalRegistryResponse.builder()
                                        .firstName("firstName001")
                                        .lastName("lastname001")
                                        .email("firstName@mail.com")
                                        .build()
                        ))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/score/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(40))));
    }

    /**
     * Simulate interaction with external APIS with error state by delay,
     * For success state use Id: 12345681
     */
    public void mockOnErrorWithDelayInLeadApi() {
        String identityNumber = "12345681";
        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/lead/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(4000)
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(
                                LeadResponse.builder()
                                        .identityNumber(identityNumber)
                                        .firstName("firstName001")
                                        .lastName("lastname001")
                                        .email("firstName@mail.com")
                                        .build()
                        ))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/judicial/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(2000)
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(true))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/national/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(2000)
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(
                                NationalRegistryResponse.builder()
                                        .firstName("firstName001")
                                        .lastName("lastname001")
                                        .email("firstName@mail.com")
                                        .build()
                        ))));

        wireMockServer.stubFor(WireMock.get(WireMock.urlMatching("/api/v1/score/verify/" + identityNumber))
                .willReturn(WireMock.aResponse()
                        .withFixedDelay(2000)
                        .withHeader("Content-Type", HEADER_JSON)
                        .withBody(new Gson().toJson(40))));
    }

    @PreDestroy
    public void preDetroy() {
        wireMockServer.stop();
    }

}
