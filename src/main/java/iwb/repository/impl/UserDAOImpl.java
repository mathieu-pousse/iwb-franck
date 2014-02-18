package iwb.repository.impl;



import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import iwb.bo.User;
import iwb.repository.UserDAO;
import iwb.transverse.RestxAuthentication;
import restx.factory.Component;

import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

import static restx.common.MorePreconditions.checkEquals;

@Component @Named("userDAO")
public class UserDAOImpl implements UserDAO{

    private RestxAuthentication restxAuth;

    public UserDAOImpl(@Named("restxAuth") RestxAuthentication  restxAuth){
        this.restxAuth = restxAuth;
    }

    public User createUser(User user) {
        restxAuth.createUser(user);
        return user;
    }

    public User updateUser(String oid, User user) {
        checkEquals("oid", oid, "user.id", user.getId());
        restxAuth.updateUser(user);
        return user;
    }

    public void deleteUser(String oid) {
        restxAuth.deleteUser(oid);
    }

    public Optional<User> getUserById(String oid) {
        return restxAuth.findUserByKey(oid);
    }

    public Iterable<User> getUsers() {
        return restxAuth.findAllUsers();
    }

    public Iterable<User> getUserByLogin(String login) {
        List<User> users = new ArrayList<User>();
        users.add(restxAuth.findUserByName(login).get());
        return users;
    }

    public void createCredentials(String oid, String password){
        restxAuth.setCredentials(oid, password);
    }
}
