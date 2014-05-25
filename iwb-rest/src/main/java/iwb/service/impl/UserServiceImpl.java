package iwb.service.impl;


import iwb.bo.User;
import iwb.repository.UserDAO;
import iwb.service.UserService;

import javax.inject.Named;

import restx.factory.Component;

import com.google.common.base.Optional;

@Component @Named("userService")
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    public UserServiceImpl(@Named("userDAO") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User createUser(User user) {
        return userDAO.createUser(user);
    }

    public User updateUser(String oid, User user) {
        return userDAO.updateUser(oid, user);
    }

    public void deleteUser(String oid) {
        userDAO.deleteUser(oid);
    }

    public Optional<User> getUserById(String oid) {
        return userDAO.getUserById(oid);
    }

    public Iterable<User> getUsers() {
        return userDAO.getUsers();
    }

    public Iterable<User> getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }
}
