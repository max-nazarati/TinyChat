package com.nazarati.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoggedClass<T> {
    protected final Logger logger;

    public LoggedClass(Class<T> clazz) {
        logger = LoggerFactory.getLogger(clazz);
        logger.trace("instantiated [{}]", clazz);
    }

}
