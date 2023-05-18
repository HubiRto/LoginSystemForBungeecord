package pl.pomoku.loginsystemlogoutplugin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Timestamp start;
    private Timestamp end;
    private String lastIP;
    @OneToOne(fetch = FetchType.LAZY)
    private LoginPlayer player;
}
