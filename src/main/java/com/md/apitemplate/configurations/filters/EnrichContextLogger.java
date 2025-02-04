package com.md.apitemplate.configurations.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class EnrichContextLogger extends OncePerRequestFilter {

    private static final ThreadLocal<Integer> sequence = ThreadLocal.withInitial(() -> 0);
    private static final String SEQUENCE_KEY = "sequence";
    private static final String CORRELATION_ID = "correlationId";
    private static final String CORRELATION_ID_HEADER_NAME = "x-correlation-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
        if (correlationId == null || correlationId.trim().isEmpty()) {
            correlationId = UUID.randomUUID().toString();
        }

        MDC.put(CORRELATION_ID, correlationId);
        MDC.put(SEQUENCE_KEY, String.valueOf(sequence.get()));

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(CORRELATION_ID);
            MDC.remove(SEQUENCE_KEY);
        }
    }

    public static int getSequence() {
        return sequence.get();
    }

    public static void incrementSequence() {
        sequence.set(sequence.get() + 1);
    }
}