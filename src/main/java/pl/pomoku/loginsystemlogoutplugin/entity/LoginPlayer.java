package pl.pomoku.loginsystemlogoutplugin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "player")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String playerUUID;
    private String playerName;
    private boolean logged;
    private String password;
    private String email;
    private boolean emailConfirm;
}
