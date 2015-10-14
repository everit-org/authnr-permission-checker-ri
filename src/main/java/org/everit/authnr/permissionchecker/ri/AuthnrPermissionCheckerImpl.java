/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.biz)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.authnr.permissionchecker.ri;

import java.util.Objects;

import org.everit.authentication.context.AuthenticationContext;
import org.everit.authnr.permissionchecker.AuthnrPermissionChecker;
import org.everit.authorization.PermissionChecker;

/**
 * Implementation of {@link AuthnrPermissionChecker} interface.
 */
public class AuthnrPermissionCheckerImpl implements AuthnrPermissionChecker {

  private AuthenticationContext authenticationContext;

  private PermissionChecker permissionChecker;

  /**
   * Constructor.
   *
   * @param authenticationContext
   *          {@link AuthenticationContext} instance.
   * @param permissionChecker
   *          {@link PermissionChecker} instance.
   * @throws NullPointerException
   *           if one of the parameter is <code>null</code>.
   */
  public AuthnrPermissionCheckerImpl(final AuthenticationContext authenticationContext,
      final PermissionChecker permissionChecker) {
    this.authenticationContext =
        Objects.requireNonNull(authenticationContext, "authenticationContext cannot be null");
    this.permissionChecker =
        Objects.requireNonNull(permissionChecker, "permissionChecker cannot be null");
  }

  @Override
  public long[] getAuthorizationScope() {
    long authorizedResourceId = authenticationContext.getCurrentResourceId();
    return permissionChecker.getAuthorizationScope(authorizedResourceId);
  }

  @Override
  public long getSystemResourceId() {
    return permissionChecker.getSystemResourceId();
  }

  @Override
  public boolean hasPermission(final long targetResourceId, final String... actions) {
    long authorizedResourceId = authenticationContext.getCurrentResourceId();
    return permissionChecker.hasPermission(authorizedResourceId, targetResourceId, actions);
  }

}
