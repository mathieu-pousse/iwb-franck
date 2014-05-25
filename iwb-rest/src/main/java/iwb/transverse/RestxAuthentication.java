package iwb.transverse;

import static iwb.AppModule.Roles.ADMIN;
import iwb.bo.User;

import java.util.Arrays;

import javax.inject.Named;

import org.bson.types.ObjectId;

import restx.admin.AdminModule;
import restx.factory.Component;
import restx.jongo.JongoCollection;
import restx.jongo.JongoUserRepository;
import restx.security.CredentialsStrategy;

@Component @Named("restxAuth")
public class RestxAuthentication extends JongoUserRepository<User> {


    public static final User defaultAdminUser = new User(new ObjectId().toString(), "admin", Arrays.asList(ADMIN, AdminModule.RESTX_ADMIN_ROLE));


    public static final RefUserByKeyStrategy<User> USER_REF_STRATEGY = new RefUserByKeyStrategy<User>() {
        @Override
        protected String getId(User user) {
            return user.getId();
        }
    };

    public RestxAuthentication(@Named("users") JongoCollection users,
                             @Named("usersCredentials") JongoCollection usersCredentials,
                             CredentialsStrategy credentialsStrategy) {
        super(
                users, usersCredentials,
                USER_REF_STRATEGY, credentialsStrategy,
                User.class, defaultAdminUser
        );
    }


}
