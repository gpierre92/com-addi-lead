package com.addi.lead.domain.interactor.validator.cases.nationalregistry;

import com.addi.lead.domain.interactor.validator.cases.nationalregistry.exception.RecordDontMatchException;
import com.addi.lead.domain.port.out.NationalRegistryPort;
import com.addi.lead.entity.Lead;
import com.addi.lead.entity.NationalRegistry;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class NationalRegistryValidatorTest {

    @InjectMocks
    private NationalRegistryValidator nationalRegistryValidator;

    @Mock
    private NationalRegistryPort nationalRegistryPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validateOnSuccess() {
        Mockito.when(nationalRegistryPort.verifyNationalRegistryById(ArgumentMatchers.any()))
                .thenReturn(Single.just(NationalRegistry.builder()
                        .firstName("firstName001")
                        .lastName("lastName001")
                        .email("email@gmail.com")
                        .build()));

        Lead lead = Lead.builder()
                .firstName("firstName001")
                .lastName("lastName001")
                .email("email@gmail.com")
                .build();
        TestObserver<Void> testObserver = nationalRegistryValidator.validate(lead).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertTerminated();
        testObserver.assertComplete();
    }

    @Test
    public void validateOnErrorDataDontMatch() {
        Mockito.when(nationalRegistryPort.verifyNationalRegistryById(ArgumentMatchers.any()))
                .thenReturn(Single.just(NationalRegistry.builder()
                        .firstName("firstName001")
                        .lastName("lastName001")
                        .email("email@gmail.com")
                        .build()));

        Lead lead = Lead.builder()
                .firstName("firstName001")
                .lastName("lastName001")
                .email("email001@gmail.com")
                .build();
        TestObserver testObserver = nationalRegistryValidator.validate(lead).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertTerminated();
        testObserver.assertError(RecordDontMatchException.class);
    }
}
