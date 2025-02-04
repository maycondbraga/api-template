package com.md.apitemplate.configurations.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.MDC;

public class SequenceIncrementingAppender extends AppenderBase<ILoggingEvent> {

    private static final String SEQUENCE_KEY = "sequence";

    @Override
    protected void append(ILoggingEvent eventObject) {
        try {
            EnrichContextLogger.incrementSequence();
            MDC.put(SEQUENCE_KEY, String.valueOf(EnrichContextLogger.getSequence()));
        } catch (Exception ignored) {}
    }
}