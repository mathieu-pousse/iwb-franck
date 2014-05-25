package iwb;

import iwb.bo.User;

import javax.inject.Named;

import restx.factory.Module;
import restx.factory.Provides;
import restx.security.BCryptCredentialsStrategy;
import restx.security.BasicPrincipalAuthenticator;
import restx.security.CredentialsStrategy;
import restx.security.RestxPrincipal;
import restx.security.RestxSession;
import restx.security.SecuritySettings;
import restx.security.SignatureKey;
import restx.security.StdBasicPrincipalAuthenticator;
import restx.security.UserService;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

@Module
public class AppModule {
	
	public static final RestxAdminPrincipal RESTX_ADMIN_PRINCIPAL = new RestxAdminPrincipal();

    public static User currentUser() {
        return (User) RestxSession.current().getPrincipal().get();
    }


    public static final class Roles {
        public static final String ADMIN = "restx-admin";
        public static final String OTHER = "other";
    }


    @Provides
    public SignatureKey signatureKey() {
        return new SignatureKey("iwb fd66f4c9-2b76-4495-9d57-d0f92d3b02f6 -8512238729272782174 iwb".getBytes(Charsets.UTF_8));
    }
    
    @Provides
    @Named("restx.admin.password")
    public String restxAdminPassword() {
        return "admin";
    }

    @Provides
    @Named("app.name")
    public String appName(){
        return "iwb";
    }

    @Provides
    public CredentialsStrategy credentialsStrategy() {
        return new BCryptCredentialsStrategy();
    }
    
    @Provides
    public BasicPrincipalAuthenticator basicPrincipalAuthenticator(
            @Named("restx.admin.passwordHash") final String adminPasswordHash, SecuritySettings securitySettings) {
        return new StdBasicPrincipalAuthenticator(new UserService<RestxAdminPrincipal>() {
            @Override
            public Optional<RestxAdminPrincipal> findUserByName(String name) {
                return "admin".equals(name) ? Optional.of(RESTX_ADMIN_PRINCIPAL) : Optional.<RestxAdminPrincipal>absent();
            }

            @Override
            public Optional<RestxAdminPrincipal> findAndCheckCredentials(String name, String passwordHash) {
                return "admin".equals(name) && adminPasswordHash.equals(passwordHash) ?
                        Optional.of(RESTX_ADMIN_PRINCIPAL) : Optional.<RestxAdminPrincipal>absent();
            }
        }, securitySettings);
    }
    
    public static class RestxAdminPrincipal implements RestxPrincipal {
        @Override
        public ImmutableSet<String> getPrincipalRoles() {
            return ImmutableSet.of(Roles.ADMIN);
        }

        @Override
        public String getName() {
            return "admin";
        }
    }

    /*@Provides
    public BasicPrincipalAuthenticator basicPrincipalAuthenticator(
            RestxAuthentication userRepository, SecuritySettings securitySettings,
            CredentialsStrategy credentialsStrategy,
            @Named("restx.admin.password") String adminPasswordHash) {
        return new StdBasicPrincipalAuthenticator(
                new StdUserService<User>(userRepository, credentialsStrategy, adminPasswordHash), securitySettings);
    } */

}
