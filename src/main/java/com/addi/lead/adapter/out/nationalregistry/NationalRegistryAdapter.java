package com.addi.lead.adapter.out.nationalregistry;

import com.addi.lead.adapter.out.nationalregistry.client.NationalRegistryClient;
import com.addi.lead.adapter.out.nationalregistry.exception.NotFoundNationalRegistryException;
import com.addi.lead.adapter.out.nationalregistry.model.NationalRegistryResponse;
import com.addi.lead.domain.port.out.NationalRegistryPort;
import com.addi.lead.entity.NationalRegistry;
import io.reactivex.Maybe;
import io.reactivex.Single;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * Class NationalRegistryAdapter
 * Adapter Class for evaluate national registry
 *
 * @author gpalacios
 */
@Slf4j
@Builder
public class NationalRegistryAdapter implements NationalRegistryPort {


    private NationalRegistryClient nationalRegistryClient;

    private Function<NationalRegistryResponse, NationalRegistry> nationalRegistryConverter;

    /**
     * verifyNationalRegistryById method that allows get info by id
     *
     * @param id String
     * @return Single<NationalRegistry>
     * @throws NotFoundNationalRegistryException when info not found in national system
     */
    @Override
    public Single<NationalRegistry> verifyNationalRegistryById(String id) {
        return Maybe.just(nationalRegistryClient.verifyById(id))
                .map(nationalRegistryResponse -> nationalRegistryConverter.apply(nationalRegistryResponse))
                .switchIfEmpty(Single.error(new NotFoundNationalRegistryException(id)));
    }
}
