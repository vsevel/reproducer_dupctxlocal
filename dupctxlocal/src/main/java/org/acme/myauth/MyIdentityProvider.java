/*
 * Copyright (c) 2023 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */
package org.acme.myauth;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import io.quarkus.security.AuthenticationFailedException;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.IdentityProvider;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class MyIdentityProvider implements IdentityProvider<MyAuthenticationRequest> {

    private static final Logger log = Logger.getLogger(MyIdentityProvider.class.getName());

    @Inject
    MyAuthManager myAuthManager;

    @Override
    public Class<MyAuthenticationRequest> getRequestType() {
        return MyAuthenticationRequest.class;
    }

    @Override
    public Uni<SecurityIdentity> authenticate(MyAuthenticationRequest request, AuthenticationRequestContext context) {

        try {
            Thread.sleep(1000L); // <<===== this will help cause the problem
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Uni<String> uni = myAuthManager.validateNonBlocking(request.getUsername());

        return uni.map(body -> createSecurityIdentity(request, body))
                .onFailure().invoke(e -> {
                    throw new AuthenticationFailedException("failed to validate token", e);
                });
    }

    private SecurityIdentity createSecurityIdentity(MyAuthenticationRequest request, String body) {
        log.info("building security identity");
        QuarkusSecurityIdentity.Builder builder = new QuarkusSecurityIdentity.Builder()
                .setPrincipal(new MyPrincipal())
                .addCredential(new PasswordCredential("bar".toCharArray()))
                .addRole("ANYONE");
        return builder.build();
    }

}
