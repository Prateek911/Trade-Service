package com.tradex.trade.service.domain.exception;

import com.tradex.trade.service.infrastructure.web.ErrorCode;
import com.tradex.trade.service.shared.exception.ApplicationException;

import java.util.concurrent.ThreadLocalRandom;

public class RecordAlreadyExistsException extends ApplicationException {

    private static final int RANDOM_MIN = 100000;
    private static final int RANDOM_MAX = 999999;

    private final Class<?> entityType;
    private final Object identifier;

    public RecordAlreadyExistsException(Class<?> entityType, Object identifier) {
        super(
                ErrorCode.ERR_409_001,
                buildMessage(entityType, identifier)
        );
        this.entityType = entityType;
        this.identifier = identifier;
    }

    private static String buildMessage(Class<?> entityType, Object identifier) {
        int randomPrefix = ThreadLocalRandom.current()
                .nextInt(RANDOM_MIN, RANDOM_MAX + 1);

        return String.format(
                "%d - Record of type %s with %s already exists",
                randomPrefix,
                entityType.getSimpleName(),
                identifier
        );
    }

    public Class<?> getEntityType() {
        return entityType;
    }

    public Object getIdentifier() {
        return identifier;
    }
}
