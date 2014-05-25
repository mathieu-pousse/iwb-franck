package iwb.repository;


import iwb.bo.User;

import com.google.common.base.Optional;

public interface UserDAO {
    public User createUser(User user);
    public User updateUser(String oid, User user);
    public void deleteUser(String oid);
    public Optional<User> getUserById(String oid);
    public Iterable<User> getUsers();
    public Iterable<User> getUserByLogin(String login);
    public void createCredentials(String oid, String password);
}
