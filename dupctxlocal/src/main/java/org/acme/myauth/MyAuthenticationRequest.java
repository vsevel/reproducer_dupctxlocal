/*
 * Copyright (c) 2023 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */
package org.acme.myauth;

import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;

public class MyAuthenticationRequest extends UsernamePasswordAuthenticationRequest {
    public MyAuthenticationRequest(String username, PasswordCredential password) {
        super(username, password);
    }
}
