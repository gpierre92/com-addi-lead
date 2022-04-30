package com.addi.lead.domain.interactor.validator.cases.judicialrecord;

import com.addi.lead.domain.port.out.JudicialRecordPort;
import com.addi.lead.entity.Lead;
import io.reactivex.Completable;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class JudicialRecordValidatorTest {

    @InjectMocks
    private JudicialRecordValidator judicialRecordValidator;

    @Mock
    private JudicialRecordPort judicialRecordPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validateOnSuccess() {
        Mockito.when(judicialRecordPort.verifyJudicialRecordById(ArgumentMatchers.any()))
                .thenReturn(Completable.complete());

        Lead lead = Lead.builder()
                .firstName("firstName001")
                .lastName("lastName001")
                .email("email@gmail.com")
                .build();
        TestObserver<Void> testObserver = judicialRecordValidator.validate(lead).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertTerminated();
        testObserver.assertComplete();

    }
}
