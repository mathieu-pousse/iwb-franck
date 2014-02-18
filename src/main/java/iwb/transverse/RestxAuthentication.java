package iwb.transverse;

import iwb.bo.User;

import com.google.common.base.Optional;
import org.bson.types.ObjectId;
import restx.Status;
import restx.WebException;
import restx.admin.AdminModule;
import restx.annotations.*;
import restx.exceptions.RestxErrors;
import restx.factory.Component;
import restx.http.HttpStatus;
import restx.jongo.JongoCollection;
import restx.jongo.JongoUserRepository;
import restx.security.CredentialsStrategy;
import restx.security.RolesAllowed;
import iwb.AppModule;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static restx.common.MorePreconditions.checkEquals;
import static iwb.AppModule.Roles.*;

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
