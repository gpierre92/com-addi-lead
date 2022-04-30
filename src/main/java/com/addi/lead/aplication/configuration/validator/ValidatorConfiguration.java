package com.addi.lead.aplication.configuration.validator;

import com.addi.lead.adapter.out.event.ProduceEventAdapter;
import com.addi.lead.adapter.out.judicialrecord.JudicialRecordAdapter;
import com.addi.lead.adapter.out.judicialrecord.client.JudicialRecordClient;
import com.addi.lead.adapter.out.lead.LeadsApiAdapter;
import com.addi.lead.adapter.out.lead.client.LeadsClient;
import com.addi.lead.adapter.out.lead.converter.LeadConverter;
import com.addi.lead.adapter.out.lead.model.LeadResponse;
import com.addi.lead.adapter.out.nationalregistry.NationalRegistryAdapter;
import com.addi.lead.adapter.out.nationalregistry.client.NationalRegistryClient;
import com.addi.lead.adapter.out.nationalregistry.converter.NationalRegistryConverter;
import com.addi.lead.adapter.out.nationalregistry.model.NationalRegistryResponse;
import com.addi.lead.adapter.out.verifyscore.FindScoreAdapter;
import com.addi.lead.adapter.out.verifyscore.client.VerifyScoreClient;
import com.addi.lead.domain.interactor.EvaluateLeadInteractor;
import com.addi.lead.domain.interactor.validator.cases.judicialrecord.JudicialRecordValidator;
import com.addi.lead.domain.interactor.validator.cases.nationalregistry.NationalRegistryValidator;
import com.addi.lead.domain.interactor.validator.composite.BusinessValidator;
import com.addi.lead.domain.interactor.validator.composite.BusinessValidatorComposite;
import com.addi.lead.domain.port.out.*;
import com.addi.lead.entity.Lead;
import com.addi.lead.entity.NationalRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * Class ValidatorConfiguration
 * Class configuration beans, client, validators
 *
 * @author gpalacios
 */
@Configuration
public class ValidatorConfiguration {

    @Autowired
    private LeadsClient leadsClient;
    @Autowired
    private JudicialRecordClient judicialRecordClient;

    @Autowired
    private NationalRegistryClient nationalRegistryClient;

    @Autowired
    private VerifyScoreClient verifyScoreClient;

    @Bean
    public Function<LeadResponse, Lead> leadsConverter() {
        return LeadConverter.builder().build();
    }

    @Bean
    public Function<NationalRegistryResponse, NationalRegistry> nationalRegistryConverter() {
        return NationalRegistryConverter.builder().build();
    }

    @Bean
    public LeadsPort leadsPort() {
        return LeadsApiAdapter.builder()
                .leadsClient(leadsClient)
                .leadConverter(this.leadsConverter())
                .build();
    }

    @Bean
    public JudicialRecordPort judicialRecordPort() {
        return JudicialRecordAdapter.builder()
                .judicialRecordClient(judicialRecordClient)
                .build();
    }

    @Bean
    public NationalRegistryPort nationalRegistryPort() {
        return NationalRegistryAdapter.builder()
                .nationalRegistryClient(nationalRegistryClient)
                .nationalRegistryConverter(this.nationalRegistryConverter())
                .build();
    }

    @Bean
    public FindScorePort verifyScoreLeadPort() {
        return FindScoreAdapter.builder()
                .verifyScoreClient(verifyScoreClient)
                .build();
    }

    @Bean
    public ProduceEventPort produceEventPort() {
        return ProduceEventAdapter.builder()
                .build();
    }

    @Bean
    public BusinessValidator<Lead> juditialRecordValidation() {
        return JudicialRecordValidator.builder()
                .judicialRecordPort(this.judicialRecordPort())
                .build();
    }

    @Bean
    public BusinessValidator<Lead> nationalRegistryValidation() {
        return NationalRegistryValidator.builder()
                .nationalRegistryPort(this.nationalRegistryPort())
                .build();
    }

    /**
     * validators method that allows add new validator for parallel execute
     *
     * @return BusinessValidator<Lead>
     */
    @Bean
    public BusinessValidator<Lead> validators() {
        BusinessValidatorComposite<Lead> validator = new BusinessValidatorComposite<>();
        validator.addValidator(this.juditialRecordValidation());
        validator.addValidator(this.nationalRegistryValidation());
        return validator;
    }

    @Bean("createEvaluateInteractor")
    public EvaluateLeadInteractor createEvaluateInteractor() {
        return EvaluateLeadInteractor.builder()
                .businessValidator(this.validators())
                .leadsPort(this.leadsPort())
                .findScorePort(this.verifyScoreLeadPort())
                .produceEventPort(this.produceEventPort())
                .build();
    }
}
