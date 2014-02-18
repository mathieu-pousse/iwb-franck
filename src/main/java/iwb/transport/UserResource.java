package iwb.transport;

import com.google.common.base.Optional;
import iwb.bo.User;
import iwb.repository.UserDAO;
import iwb.transverse.RestxAuthentication;
import restx.annotations.*;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import javax.inject.Named;

@Component @RestxResource
@PermitAll
public class UserResource {

    private UserDAO userDAO;

    public UserResource(@Named("userDAO") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @GET("/users")
    public Iterable<User> findUsers(){
        return userDAO.getUsers();
    }

    @POST("/users")
    public User createUser(User user){
        return userDAO.createUser(user);
    }

    @PUT("/users/{oid}")
    public User updateUser(String oid, User user){
        return userDAO.updateUser(oid, user);
    }

    @DELETE("/users/{oid}")
    public void deleteUser(String oid){
        userDAO.deleteUser(oid);
    }

    @GET("/users/{oid}")
    public Optional<User> findUserById(String oid){
        return userDAO.getUserById(oid);
    }
}
