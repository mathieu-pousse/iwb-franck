package iwb;

import com.google.common.base.Charsets;
import iwb.bo.User;
import iwb.transverse.RestxAuthentication;
import restx.mongo.MongoModule;
import restx.security.*;
import restx.factory.Module;
import restx.factory.Provides;

import javax.inject.Named;

@Module
public class AppModule {

    public static User currentUser() {
        return (User) RestxSession.current().getPrincipal().get();
    }

    public static final class Roles {
        public static final String ADMIN = "admin";
        public static final String OTHER = "other";
    }

    @Provides
    public SignatureKey signatureKey() {
        return new SignatureKey("iwb fd66f4c9-2b76-4495-9d57-d0f92d3b02f6 -8512238729272782174 iwb".getBytes(Charsets.UTF_8));
    }
    @Provides
    @Named("restx.admin.password")
    public String restxAdminPassword() {
        return "2136";
    }

    @Provides
    @Named("app.name")
    public String appName(){
        return "iwb";
    }

    @Provides @Named(MongoModule.MONGO_DB_NAME)
    public String dbName() {
        return "restx-hellomongo";
    }

    @Provides
    public CredentialsStrategy credentialsStrategy() {
        return new BCryptCredentialsStrategy();
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
