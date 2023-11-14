/*
 * Copyright (c) 2023 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */
package org.acme;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.ws.rs.container.ContainerRequestContext;

import org.jboss.resteasy.reactive.server.ServerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.common.vertx.ContextLocals;
import io.vertx.core.Vertx;

public class MyFilter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    static AtomicInteger id = new AtomicInteger();
    static AtomicInteger errors = new AtomicInteger();

    @ServerRequestFilter(preMatching = true)
    public void preMatchingFilter(ContainerRequestContext requestContext) throws InterruptedException {

        String path = requestContext.getUriInfo().getPath();

        Optional<Object> ctx = ContextLocals.get("ctx");
        logger.info("receive http requestContext " + path + " ctx=" + Vertx.currentContext() + " value=" + ctx
                + " total errors=" + errors);

        if (ctx.isEmpty()) {
            String value = "ID:" + id.incrementAndGet();
            ContextLocals.put("ctx", value);
            logger.info("initialized context with " + value);
        } else {
            errors.incrementAndGet();
            ContextLocals.put("corrupted", true);
            logger.error("=========> ctx already initialized!!!");
            throw new RuntimeException("oops");
        }
    }

}
