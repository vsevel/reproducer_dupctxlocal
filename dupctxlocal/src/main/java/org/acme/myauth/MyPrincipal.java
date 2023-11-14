/*
 * Copyright (c) 2023 by Bank Lombard Odier & Co Ltd, Geneva, Switzerland. This software is subject
 * to copyright protection under the laws of Switzerland and other countries. ALL RIGHTS RESERVED.
 *
 */
package org.acme.myauth;

import java.io.Serializable;
import java.security.Principal;

public class MyPrincipal implements Principal, Serializable {
    @Override
    public String getName() {
        return "foo";
    }
}
