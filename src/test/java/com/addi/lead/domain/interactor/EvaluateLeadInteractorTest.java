package com.addi.lead.domain.interactor;

import com.addi.lead.domain.interactor.exception.VerifyScoreException;
import com.addi.lead.domain.interactor.validator.composite.BusinessValidator;
import com.addi.lead.domain.port.out.FindScorePort;
import com.addi.lead.domain.port.out.LeadsPort;
import com.addi.lead.domain.port.out.ProduceEventPort;
import com.addi.lead.entity.Lead;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EvaluateLeadInteractorTest {

    @InjectMocks
    private EvaluateLeadInteractor evaluateLeadInteractor;

    @Mock
    private BusinessValidator<Lead> businessValidator;

    @Mock
    private LeadsPort leadsPort;

    @Mock
    private FindScorePort findScorePort;

    @Mock
    private ProduceEventPort produceEventPort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doEvaluateOnSuccess() {

        Mockito.when(leadsPort.getLeadById(ArgumentMatchers.any())).thenReturn(Single.just(Lead.builder().build()));
        Mockito.when(businessValidator.validate(ArgumentMatchers.any())).thenReturn(Completable.complete());
        Mockito.when(findScorePort.findScoreLeadById(ArgumentMatchers.any())).thenReturn(Single.just(65));
        Mockito.when(produceEventPort.produce(ArgumentMatchers.any())).thenReturn(Completable.complete());

        String identityNumber = "12345678";
        TestObserver<Void> testObserver = evaluateLeadInteractor.doEvaluate(identityNumber).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertTerminated();
        testObserver.assertComplete();
    }

    @Test
    void doEvaluateOnErrorWithIdIsEmptyOrNull() {

        Mockito.when(leadsPort.getLeadById(ArgumentMatchers.any())).thenReturn(Single.just(Lead.builder().build()));
        Mockito.when(businessValidator.validate(ArgumentMatchers.any())).thenReturn(Completable.complete());
        Mockito.when(findScorePort.findScoreLeadById(ArgumentMatchers.any())).thenReturn(Single.just(65));
        Mockito.when(produceEventPort.produce(ArgumentMatchers.any())).thenReturn(Completable.complete());

        Random r = new Random();
        List<String> ids = Arrays.asList("", null);
        String identityNumber = ids.get(r.nextInt(ids.size()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> evaluateLeadInteractor.doEvaluate(identityNumber));
    }

    @Test
    void doEvaluateOnErrorWithScoreIsLessThan60() {


        int scoreValue = 59;
        Mockito.when(leadsPort.getLeadById(ArgumentMatchers.any())).thenReturn(Single.just(Lead.builder().build()));
        Mockito.when(businessValidator.validate(ArgumentMatchers.any())).thenReturn(Completable.complete());
        Mockito.when(findScorePort.findScoreLeadById(ArgumentMatchers.any())).thenReturn(Single.just(scoreValue));
        Mockito.when(produceEventPort.produce(ArgumentMatchers.any())).thenReturn(Completable.complete());

        String identityNumber = "12345678";

        TestObserver testObserver = evaluateLeadInteractor.doEvaluate(identityNumber).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertTerminated();
        testObserver.assertError(VerifyScoreException.class);

    }

    @Test
    void doEvaluateOnErrorWithScoreIsEqualThan60() {


        int scoreValue = 60;
        Mockito.when(leadsPort.getLeadById(ArgumentMatchers.any())).thenReturn(Single.just(Lead.builder().build()));
        Mockito.when(businessValidator.validate(ArgumentMatchers.any())).thenReturn(Completable.complete());
        Mockito.when(findScorePort.findScoreLeadById(ArgumentMatchers.any())).thenReturn(Single.just(scoreValue));
        Mockito.when(produceEventPort.produce(ArgumentMatchers.any())).thenReturn(Completable.complete());

        String identityNumber = "12345678";

        TestObserver testObserver = evaluateLeadInteractor.doEvaluate(identityNumber).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertTerminated();
        testObserver.assertError(VerifyScoreException.class);

    }
}
