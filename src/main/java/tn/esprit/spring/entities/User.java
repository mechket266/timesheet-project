package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ AJOUT : @Column + getter
    @Column(name = "first_name")
    @JsonProperty("firstName")
    private String firstName;

    // ✅ AJOUT : @Column
    @Column(name = "last_name")
    private String lastName;

    // ✅ AJOUT : @Column
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    // ✅ AJOUT : @Column
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() { }

    public User(String firstName, String lastName, Date dateNaissance, Role role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateNaissance = dateNaissance;
        this.role = role;
    }

    public User(Long id, String firstName, String lastName, Date dateNaissance, Role role) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateNaissance = dateNaissance;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateNaissance="
                + dateNaissance + ", role=" + role + "]";
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // ✅ AJOUT CRITIQUE : Getter pour firstName (manquait !)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
    if (lastName != null) {
        return lastName + "-v1";
    }
    return lastName;
}

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
