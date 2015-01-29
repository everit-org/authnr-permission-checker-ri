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
package org.everit.osgi.authnr.permissionchecker.ri;

public final class AuthnrPermissionCheckerRIConstants {

    public static final String DEFAULT_SERVICE_DESCRIPTION = "Default Authenticated Authorization Permission Checker";

    public static final String SERVICE_FACTORYPID =
            "org.everit.osgi.authnr.permissionchecker.ri.AuthnrPermissionChecker";

    public static final String PROP_AUTHENTICATION_CONTEXT = "authenticationContext.target";

    public static final String PROP_PERMISSION_CHECKER = "permissionChecker.target";

    private AuthnrPermissionCheckerRIConstants() {
    }

}
