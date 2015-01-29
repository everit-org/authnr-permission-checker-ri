/**
 * This file is part of Everit - Authentication Authorization Permission Checker RI Tests.
 *
 * Everit - Authentication Authorization Permission Checker RI Tests is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Everit - Authentication Authorization Permission Checker RI Tests is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Everit - Authentication Authorization Permission Checker RI Tests.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.everit.osgi.authnr.permissionchecker.ri.tests.mock;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.authnr.permissionchecker.ri.tests.AuthnrPermissionCheckerTest;
import org.everit.osgi.authnr.permissionchecker.ri.tests.mock.MockAuthenticationContextComponent;
import org.everit.osgi.authorization.PermissionChecker;
import org.junit.Assert;

@Component
@Service
public class MockPermissionCheckerComponent implements PermissionChecker {

    public static final long SYSTEM_RESOURCE_ID = 0;

    public static final boolean HAS_PERMISSION = false;

    @Override
    public long[] getAuthorizationScope(final long authorizedResourceId) {
        Assert.assertEquals(MockAuthenticationContextComponent.CURRENT_RESOURCE_ID, authorizedResourceId);
        return new long[] { authorizedResourceId };
    }

    @Override
    public long getSystemResourceId() {
        return SYSTEM_RESOURCE_ID;
    }

    @Override
    public boolean hasPermission(final long authorizedResourceId, final long targetResourceId, final String... actions) {
        Assert.assertEquals(MockAuthenticationContextComponent.CURRENT_RESOURCE_ID, authorizedResourceId);
        Assert.assertEquals(AuthnrPermissionCheckerTest.TARGET_RESOURCE_ID, targetResourceId);
        Assert.assertArrayEquals(AuthnrPermissionCheckerTest.ACTIONS, actions);
        return HAS_PERMISSION;
    }

}
