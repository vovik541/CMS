package app.entities;

import java.util.Objects;

public class User {
    private Role role = Role.USER;

    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private int customerId;

    public static class Builder {

        private Role role = Role.USER;
        private String firstName = null;
        private String lastName = null;
        private String email = null;
        private String login = null;
        private String password = null;
        private int customerId = 0;

        public Builder() {
        }

        public Builder setCustomerId(int customerId){
            this.customerId = customerId;
            return this;
        }

        public Builder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRole(int role) {

            if(role == 1){
                this.role = Role.USER;
            }else if (role == 2){
                this.role = Role.SPEAKER;
            }else if (role == 3){
                this.role = Role.MODER;
            }else if (role == 4){
                this.role = Role.ADMIN;
            }else {
                this.role = Role.USER;
            }

            return this;
        }

        public User build() {
            return new User(this.customerId, this.firstName, this.lastName,
                    this.email, this.login, this.password, this.role);
        }
    }

    public User(){

    }

    public User(int customerId, String firstName, String lastName,
                String email, String login, String password, Role role) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String firstName, String lastName,String email,String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public User(int customerId, String firstName, String lastName, String email,
                String login, int role) {
        this.customerId = customerId;
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

        if (!Objects.equals(login, user.login)) return false;
        return Objects.equals(password, user.password);

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}