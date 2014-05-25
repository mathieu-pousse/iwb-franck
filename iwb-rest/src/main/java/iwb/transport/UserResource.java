package iwb.transport;

import iwb.bo.User;
import iwb.repository.UserDAO;

import javax.inject.Named;

import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.PUT;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.google.common.base.Optional;

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
