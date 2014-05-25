package iwb.repository.impl;



import static restx.common.MorePreconditions.checkEquals;
import iwb.bo.User;
import iwb.repository.UserDAO;
import iwb.transverse.RestxAuthentication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import restx.factory.Component;

import com.google.common.base.Optional;

/**
 * User DAO implements methods to create, retreive, update and delete users
 */
@Component @Named("userDAO")
public class UserDAOImpl implements UserDAO{

    private RestxAuthentication restxAuth;

    public UserDAOImpl(@Named("restxAuth") RestxAuthentication  restxAuth){
        this.restxAuth = restxAuth;
    }

    /**
     * Creates a new user
     * @param user
     * @return
     */
    public User createUser(User user) {
        restxAuth.createUser(user);
        return user;
    }

    /**
     * Updates user that matches the oid parameter
     * @param oid
     * @param user
     * @return
     */
    public User updateUser(String oid, User user) {
        checkEquals("oid", oid, "user.id", user.getId());
        restxAuth.updateUser(user);
        return user;
    }

    /**
     * Deletes the user that matches the oid parameter
     * @param oid
     */
    public void deleteUser(String oid) {
        restxAuth.deleteUser(oid);
    }

    /**
     * Returns the user that matches the oid parameter
     * @param oid
     * @return
     */
    public Optional<User> getUserById(String oid) {
        return restxAuth.findUserByKey(oid);
    }

    /**
     * Returns all the users
     * @return
     */
    public Iterable<User> getUsers() {
        return restxAuth.findAllUsers();
    }

    /**
     * Return the that matches the le login parameter
     * @param login
     * @return
     */
    public Iterable<User> getUserByLogin(String login) {
        List<User> users = new ArrayList<User>();
        users.add(restxAuth.findUserByName(login).get());
        return users;
    }

    /**
     * Creates new credentials for a user
     * @param oid
     * @param password
     */
    public void createCredentials(String oid, String password){
        restxAuth.setCredentials(oid, password);
    }
}
