package dk.wiingreen.starfinder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
class User {
    @Id
    private String username;
    private String password;

    protected User() {
    }

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    Character createCharacter(String name) {
        Character character = new Character();
        character.setName(name);
        character.setOwner(this);
        return character;
    }

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    String getPassword() {
        return password;
    }
}
