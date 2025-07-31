package dk.wiingreen.starfinder;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "characters")
class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk__characters__users_owner"))
    private User owner;

    void setName(String name) {
        this.name = name;
    }

    void setOwner(User owner) {
        this.owner = owner;
    }

    UUID getId() {
        return id;
    }
}
