package com.nazarati.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoggedClass<T> {
    protected final Logger logger;

    protected LoggedClass(Class<T> clazz) {
        logger = LoggerFactory.getLogger(clazz);
        logger.trace("instantiated [{}]", clazz);
    }

}
