package dk.wiingreen.starfinder.campaign;

import dk.wiingreen.starfinder.auth.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "campaigns")
public class Campaign {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @ManyToOne private User owner;

  protected Campaign() {}

  public Campaign(String name, User owner) {
    this.name = name;
    this.owner = owner;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(@NotBlank String value) {
    this.name = value;
  }
}
