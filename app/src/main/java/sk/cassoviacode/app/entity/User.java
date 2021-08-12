package sk.cassoviacode.app.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "app_user")
@SQLDelete(sql = "UPDATE app_user SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Email(message = "Provide a valid email")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Size(max = 255)
    @Column(name = "login", unique = true)
    private String login;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
