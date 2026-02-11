package com.tradex.trade.service.shared.exception;

import com.tradex.trade.service.infrastructure.web.ErrorCode;

import java.util.concurrent.ThreadLocalRandom;

public class PersistanceFailureException extends ApplicationException {

    private static final int RANDOM_MIN = 100000;
    private static final int RANDOM_MAX = 999999;

    private final Class<?> entityType;
    private final Object identifier;

    public PersistanceFailureException(Class<?> entityType, Object identifier) {
        super(
                ErrorCode.ERR_500_001,
                buildMessage(entityType, identifier)
        );
        this.entityType = entityType;
        this.identifier = identifier;
    }

    private static String buildMessage(Class<?> entityType, Object identifier) {
        int randomPrefix = ThreadLocalRandom.current()
                .nextInt(RANDOM_MIN, RANDOM_MAX + 1);

        return String.format(
                "%d - Failed to persist entity of type %s with identifier %s",
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
