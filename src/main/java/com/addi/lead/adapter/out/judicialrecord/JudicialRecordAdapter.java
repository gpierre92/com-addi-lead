package com.addi.lead.adapter.out.judicialrecord;

import com.addi.lead.adapter.out.judicialrecord.client.JudicialRecordClient;
import com.addi.lead.adapter.out.judicialrecord.exception.JudicialRecordException;
import com.addi.lead.aplication.util.ValidateUtil;
import com.addi.lead.domain.port.out.JudicialRecordPort;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * Class JudicialRecordAdapter
 * Adapter Class for evaluate judicial record
 *
 * @author gpalacios
 */
@Slf4j
@Builder
public class JudicialRecordAdapter implements JudicialRecordPort {

    private JudicialRecordClient judicialRecordClient;

    /**
     * verifyJudicialRecordById method that allows get info by id
     *
     * @param id String
     * @return Completable
     * @throws JudicialRecordException when info not found in judicial system
     */
    @Override
    public Completable verifyJudicialRecordById(String id) {
        ValidateUtil.isNullValidate(this.getClass(), id);
        return Single.just(judicialRecordClient.verifyById(id))
                .flatMapCompletable(isValid -> {
                    if (!isValid) {
                        log.error("Judicial Record not found");
                        return Completable.error(new JudicialRecordException(id));
                    }
                    return Completable.complete();
                });
    }


}
