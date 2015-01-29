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
package org.everit.osgi.authnr.permissionchecker.ri.tests;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.everit.osgi.authnr.permissionchecker.AuthnrPermissionChecker;
import org.everit.osgi.authnr.permissionchecker.ri.tests.mock.MockAuthenticationContextComponent;
import org.everit.osgi.authnr.permissionchecker.ri.tests.mock.MockPermissionCheckerComponent;
import org.everit.osgi.dev.testrunner.TestRunnerConstants;
import org.junit.Assert;
import org.junit.Test;

@Component(immediate = true, configurationFactory = false, policy = ConfigurationPolicy.OPTIONAL)
@Properties({
        @Property(name = TestRunnerConstants.SERVICE_PROPERTY_TESTRUNNER_ENGINE_TYPE, value = "junit4"),
        @Property(name = TestRunnerConstants.SERVICE_PROPERTY_TEST_ID, value = "AuthnrPermissionCheckerTest"),
        @Property(name = "authnrPermissionChecker.target"),
})
@Service(value = AuthnrPermissionCheckerTest.class)
public class AuthnrPermissionCheckerTest {

    public static final long TARGET_RESOURCE_ID = 2;

    public static final String[] ACTIONS = { "a1", "a2" };

    @Reference(bind = "setAuthnrPermissionChecker")
    private AuthnrPermissionChecker authnrPermissionChecker;

    public void setAuthnrPermissionChecker(final AuthnrPermissionChecker authnrPermissionChecker) {
        this.authnrPermissionChecker = authnrPermissionChecker;
    }

    @Test
    public void testComplex() {

        Assert.assertEquals(MockPermissionCheckerComponent.SYSTEM_RESOURCE_ID,
                authnrPermissionChecker.getSystemResourceId());

        Assert.assertArrayEquals(new long[] { MockAuthenticationContextComponent.CURRENT_RESOURCE_ID },
                authnrPermissionChecker.getAuthorizationScope());

        Assert.assertEquals(MockPermissionCheckerComponent.HAS_PERMISSION,
                authnrPermissionChecker.hasPermission(TARGET_RESOURCE_ID, ACTIONS));
    }
}
