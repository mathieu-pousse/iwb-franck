package iwb.service;


import com.google.common.base.Optional;
import iwb.bo.User;

public interface UserService {
    public User createUser(User user);
    public User updateUser(String oid, User user);
    public void deleteUser(String oid);
    public Optional<User> getUserById(String oid);
    public Iterable<User> getUsers();
    public Iterable<User> getUserByLogin(String login);
}