package boot.bean;

import org.springframework.stereotype.Repository;

@Repository
public class Admin {
    private String username;
    private String password;
    private String email;
    private String emailPassword;

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", emailPassword='" + emailPassword + '\'' +
                '}';
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Admin() {
    }

    public Admin(String username, String password, String email, String emailPassword) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.emailPassword = emailPassword;
    }
}
