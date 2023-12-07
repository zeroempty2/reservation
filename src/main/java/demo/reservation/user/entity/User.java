package demo.reservation.user.entity;

import demo.reservation.reservation.entity.UserReservation;
import demo.reservation.util.TimeStamped;
import demo.reservation.util.enums.UserRoleEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends TimeStamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 25, nullable = false, unique = true)
  private String accountName;

  @Column(length = 15, nullable = false, unique = true)
  private String username;

  @Column
  @Enumerated(EnumType.STRING)
  private UserRoleEnum role;

  @Column(nullable = false)
  private String password;

  //생성자
  @Builder
  public User(String username, String password, String accountName, UserRoleEnum role){
    this.username = username;
    this.accountName = accountName;
    this.password = password;
    this.role = role;
  }

  //연관관계
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<UserReservation> userReservations = new HashSet<>();
}
