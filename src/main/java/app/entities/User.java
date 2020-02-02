package app.entities;

public class User {
    private Role role = Role.USER;

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private int customerId;

    public User() {
    }

    public User(String firstName, String lastName,String email,String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public User(String firstName, String lastName, String email,
                String login, int role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;

        if(role == 1){
            this.role = Role.USER;
        }else if (role == 2){
            this.role = Role.SPEAKER;
        }else if (role == 3){
            this.role = Role.MODER;
        }else{
            this.role = Role.ADMIN;
        }

    }

    public User(String firstName, String lastName, String email,
                String login, String password, int role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;

        if(role == 1){
            this.role = Role.USER;
        }else if (role == 2){
            this.role = Role.SPEAKER;
        }else if (role == 3){
            this.role = Role.MODER;
        }else{
            this.role = Role.ADMIN;
        }

    }

    public User(int customerId, String firstName, String lastName,
                String email, String login, String password, int role) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;

        if(role == 1){
            this.role = Role.USER;
        }else if (role == 2){
            this.role = Role.SPEAKER;
        }else if (role == 3){
            this.role = Role.MODER;
        }else{
            this.role = Role.ADMIN;
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "fn='" + firstName + '\'' +
                ", ln='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}