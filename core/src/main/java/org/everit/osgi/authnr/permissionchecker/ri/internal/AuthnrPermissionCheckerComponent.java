/**
 * This file is part of Everit - Authenticated Authorization Permission Cheker RI.
 *
 * Everit - Authenticated Authorization Permission Cheker RI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Authenticated Authorization Permission Cheker RI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Authenticated Authorization Permission Cheker RI.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.authnr.permissionchecker.ri.internal;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.authentication.context.AuthenticationContext;
import org.everit.osgi.authnr.permissionchecker.AuthnrPermissionChecker;
import org.everit.osgi.authnr.permissionchecker.ri.AuthnrPermissionCheckerRIConstants;
import org.everit.osgi.authorization.PermissionChecker;
import org.osgi.framework.Constants;

@Component(name = AuthnrPermissionCheckerRIConstants.SERVICE_FACTORYPID, configurationFactory = true,
        policy = ConfigurationPolicy.REQUIRE, metatype = true)
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, propertyPrivate = false,
                value = AuthnrPermissionCheckerRIConstants.DEFAULT_SERVICE_DESCRIPTION),
        @Property(name = AuthnrPermissionCheckerRIConstants.PROP_AUTHENTICATION_CONTEXT),
        @Property(name = AuthnrPermissionCheckerRIConstants.PROP_PERMISSION_CHECKER)
})
@Service
public class AuthnrPermissionCheckerComponent implements AuthnrPermissionChecker {

    @Reference(bind = "setAuthenticationContext")
    private AuthenticationContext authenticationContext;

    @Reference(bind = "setPermissionChecker")
    private PermissionChecker permissionChecker;

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

    public void setAuthenticationContext(final AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public void setPermissionChecker(final PermissionChecker permissionChecker) {
        this.permissionChecker = permissionChecker;
    }

}
