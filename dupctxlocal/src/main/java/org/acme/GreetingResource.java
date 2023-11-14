/*
 * Copyright (c) 2023 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */
package org.acme;

import java.util.Optional;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.common.vertx.ContextLocals;

@Path("/hello")
public class GreetingResource {

    private static final Logger logger = LoggerFactory.getLogger(GreetingResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    // @RolesAllowed("ANYONE")
    public String hello() throws InterruptedException {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/post")
    @RolesAllowed("ANYONE")
    public String helloPOST() throws InterruptedException {
        Optional<Object> ctx = ContextLocals.get("ctx");
        Optional<Object> corrupted = ContextLocals.get("corrupted");
        logger.info("in hello service, ctx=" + ctx);
        return "Hello from RESTEasy Reactive with ctx=" + ctx + " is_corrupted=" + corrupted;
    }
}
