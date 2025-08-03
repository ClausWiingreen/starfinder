package dk.wiingreen.starfinder.character;

import dk.wiingreen.starfinder.auth.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk__characters__users_owner"))
    private User owner;

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
