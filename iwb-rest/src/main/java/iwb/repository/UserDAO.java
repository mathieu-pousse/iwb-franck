package iwb.repository;


import com.google.common.base.Optional;
import iwb.bo.User;

public interface UserDAO {
    public User createUser(User user);
    public User updateUser(String oid, User user);
    public void deleteUser(String oid);
    public Optional<User> getUserById(String oid);
    public Iterable<User> getUsers();
    public Iterable<User> getUserByLogin(String login);
    public void createCredentials(String oid, String password);
}
