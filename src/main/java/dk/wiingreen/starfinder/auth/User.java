package dk.wiingreen.starfinder.auth;

import dk.wiingreen.starfinder.character.Character;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;

    protected User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Character createCharacter(String name) {
        var character = new Character();
        character.setName(name);
        character.setOwner(this);
        return character;
    }

    public String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}
