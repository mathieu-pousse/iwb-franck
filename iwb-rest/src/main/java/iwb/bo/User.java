package iwb.bo;


import java.util.ArrayList;
import java.util.Collection;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import restx.security.RestxPrincipal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.ImmutableSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements RestxPrincipal{

    @Id @ObjectId
    private String id;
    private Link link;
    private String name;
    private String email;
    private String firstName;
    private String lastName;
    private Collection<String> roles;

    public User(String id, String name, Collection<String> roles){
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.roles = new ArrayList<String>();
    }

    public User(){
        this.roles = new ArrayList<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override @JsonIgnore
    public ImmutableSet<String> getPrincipalRoles() {
        return ImmutableSet.copyOf(roles);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
