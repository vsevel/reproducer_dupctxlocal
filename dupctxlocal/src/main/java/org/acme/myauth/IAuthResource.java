/*
 * Copyright (c) 2023 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */
package org.acme.myauth;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;

@Path("/hello")
@RegisterRestClient(configKey = "customauth")
public interface IAuthResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    Uni<String> validate();
}
