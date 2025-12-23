package com.tradex.trade.service.common.exception;

import com.tradex.trade.service.domain.common.ErrorCode;

import java.util.concurrent.ThreadLocalRandom;

public class RecordNotFoundException extends ApplicationException {

    private static final int RANDOM_MIN = 100000;
    private static final int RANDOM_MAX = 999999;

    private final Class<?> entityType;
    private final Object identifier;

    public RecordNotFoundException(Class<?> entityType, Object identifier) {
        super(
                ErrorCode.ERR_404_001,
                buildMessage(entityType, identifier)
        );
        this.entityType = entityType;
        this.identifier = identifier;
    }

    private static String buildMessage(Class<?> entityType, Object identifier) {
        int randomPrefix = ThreadLocalRandom.current()
                .nextInt(RANDOM_MIN, RANDOM_MAX + 1);

        return String.format(
                "%d - Record of type %s with %s not found",
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
