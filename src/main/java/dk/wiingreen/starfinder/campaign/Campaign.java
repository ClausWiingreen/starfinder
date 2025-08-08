package dk.wiingreen.starfinder.campaign;

import dk.wiingreen.starfinder.auth.User;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "campaigns")
public class Campaign {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @ManyToOne private User owner;
}
