/*
 * Copyright (c) 2023 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */
package org.acme.myauth;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.cache.CacheResult;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class MyAuthManager {

    @Inject
    @RestClient
    IAuthResource auth;

    @CacheResult(cacheName = "mycache") // <<===== this will cause problem
    public Uni<String> validateNonBlocking(String name) {
        return auth.validate();
    }
}
