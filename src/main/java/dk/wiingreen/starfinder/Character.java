package dk.wiingreen.starfinder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

@Entity
class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @ManyToOne
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
