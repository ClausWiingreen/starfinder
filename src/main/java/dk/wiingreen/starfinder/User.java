package dk.wiingreen.starfinder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AppUser")
class User {
    @Id
    private String username;

    void setUsername(String username) {
        this.username = username;
    }

    Character createCharacter(String name) {
        Character character = new Character();
        character.setName(name);
        character.setOwner(this);
        return character;
    }
}
