package org.slf4j;

import java.util.Map;
import java.util.function.Supplier;

public interface LoggerMessageBuilder {
    /**
     * Log message at the given level, marker, MDC fields and throwable if expression is fulfilled.
     *
     * @see Logger
     */
    void log(String message, Object... arguments);

    /**
     * Log message at the given level, marker, MDC fields and throwable if expression is fulfilled.
     *
     * @see Logger
     */
    void log(Supplier<String> message);

    /**
     * Log message only if expression is fulfilled.
     *
     * @return this instance
     */
    LoggerMessageBuilder logIf(boolean expression);

    /**
     * Log message only if expression is fulfilled.
     *
     * @return this instance
     */
    LoggerMessageBuilder logIf(Supplier<Boolean> expression);

    /**
     * Use given throwable when logging message.
     *
     * @return this instance
     */
    LoggerMessageBuilder withThrowable(Throwable throwable);

    /**
     * Use given marker when logging message.
     *
     * @return this instance
     */
    LoggerMessageBuilder withMarker(Marker marker);

    /**
     * Set given fields in MDC when logging message.
     *
     * @return this instance
     */
    LoggerMessageBuilder withFields(Map<String, String> fields);

    /**
     * Set given field in MDC when logging message.
     *
     * @return this instance
     */
    LoggerMessageBuilder withFields(String key, String value);

    /**
     * Set given fields in MDC when logging message.
     *
     * @return this instance
     */
    LoggerMessageBuilder withFields(String key1, String value1, String key2, String value2);

    /**
     * Set given fields in MDC when logging message.
     *
     * @return this instance
     */
    LoggerMessageBuilder withFields(String key1, String value1, String key2, String value2, String key3, String value3);

    enum DiscardingLoggerMessageBuilder implements LoggerMessageBuilder {
        INSTANCE;

        @Override
        public void log(String message, Object... arguments) {
            // discard
        }

        @Override
        public void log(Supplier<String> message) {
            // discard
        }

        @Override
        public LoggerMessageBuilder logIf(boolean expression) {
            return this;
        }

        @Override
        public LoggerMessageBuilder logIf(Supplier<Boolean> expression) {
            return this;
        }

        @Override
        public LoggerMessageBuilder withThrowable(Throwable throwable) {
            return this;
        }

        @Override
        public LoggerMessageBuilder withMarker(Marker marker) {
            return this;
        }

        @Override
        public LoggerMessageBuilder withFields(Map<String, String> fields) {
            return this;
        }

        @Override
        public LoggerMessageBuilder withFields(String key, String value) {
            return this;
        }

        @Override
        public LoggerMessageBuilder withFields(String key1, String value1, String key2, String value2) {
            return this;
        }

        @Override
        public LoggerMessageBuilder withFields(String key1, String value1, String key2, String value2, String key3, String value3) {
            return this;
        }
    }
}