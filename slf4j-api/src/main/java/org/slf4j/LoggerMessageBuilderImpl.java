package org.slf4j;

import org.slf4j.event.Level;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class LoggerMessageBuilderImpl implements LoggerMessageBuilder {
    private final Logger logger;
    private final Level level;

    /**
     * can be null
     */
    private Map<String, String> mdcFields;
    /**
     * can be null
     */
    private Throwable throwable;
    /**
     * can be null
     */
    private Marker marker;

    public LoggerMessageBuilderImpl(Logger logger, Level level) {
        this.logger = logger;
        this.level = level;
    }

    @Override
    public void log(String message, Object... arguments) {
        if (mdcFields != null) {
            MDC.runWith(mdcFields, () -> selectLevel(message, arguments));
        } else {
            selectLevel(message, arguments);
        }
    }

    @Override
    public void log(Supplier<String> message) {
        if (mdcFields != null) {
            MDC.runWith(mdcFields, () -> selectLevel(message.get(), null));
        } else {
            selectLevel(message.get(), null);
        }
    }

    private void selectLevel(String message, Object[] arguments) {
        switch (level) {
            case TRACE:
                // TODO
                break;
            case DEBUG:
                // TODO
                break;
            case INFO:
                logInfo(message, arguments);
                break;
            case WARN:
                // TODO
                break;
            case ERROR:
                // TODO
                break;
            default:
                throw new IllegalStateException("unknown level=" + level);
        }
    }

    /**
     * @param arguments can be null
     */
    private void logInfo(String message, Object[] arguments) {
        if (throwable != null) {
            if (marker != null) {
                if (arguments != null) {
                    logger.info(marker, message, copyOf(arguments, throwable));
                } else {
                    logger.info(marker, message, throwable);
                }
            } else {
                if (arguments != null) {
                    logger.info(message, copyOf(arguments, throwable));
                } else {
                    logger.info(message, throwable);
                }
            }
        } else {
            if (marker != null) {
                if (arguments != null) {
                    logger.info(marker, message, arguments);
                } else {
                    logger.info(marker, message);
                }
            } else {
                if (arguments != null) {
                    logger.info(message, arguments);
                } else {
                    logger.info(message);
                }
            }
        }
    }

    private Object[] copyOf(Object[] array, Object objectToAdd) {
        Object[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[newArray.length] = objectToAdd;
        return newArray;
    }

    @Override
    public LoggerMessageBuilder logIf(boolean expression) {
        return expression ? this : DiscardingLoggerMessageBuilder.INSTANCE;
    }

    @Override
    public LoggerMessageBuilder logIf(Supplier<Boolean> expression) {
        return expression.get() ? this : DiscardingLoggerMessageBuilder.INSTANCE;
    }

    @Override
    public LoggerMessageBuilder withThrowable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    @Override
    public LoggerMessageBuilder withMarker(Marker marker) {
        this.marker = marker;
        return this;
    }

    @Override
    public LoggerMessageBuilder withFields(Map<String, String> fields) {
        mdcFields = fields;
        return this;
    }

    @Override
    public LoggerMessageBuilder withFields(String key, String value) {
        mdcFields = new TreeMap<>();
        mdcFields.put(key, value);
        return this;
    }

    @Override
    public LoggerMessageBuilder withFields(String key1, String value1, String key2, String value2) {
        mdcFields = new TreeMap<>();
        mdcFields.put(key1, value1);
        mdcFields.put(key2, value2);
        return this;
    }

    @Override
    public LoggerMessageBuilder withFields(String key1, String value1, String key2, String value2, String key3, String value3) {
        mdcFields = new TreeMap<>();
        mdcFields.put(key1, value1);
        mdcFields.put(key2, value2);
        mdcFields.put(key3, value3);
        return this;
    }
}
