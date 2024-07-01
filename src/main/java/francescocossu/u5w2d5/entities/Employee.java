package francescocossu.u5w2d5.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Employee {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String avatarUrl;
    private String password;


    public Employee(String username, String name, String surname, String email, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.avatarUrl = "https://ui-avatars.com/api/?name=" + name + "+" + surname;
        this.password = password;
    }
}
