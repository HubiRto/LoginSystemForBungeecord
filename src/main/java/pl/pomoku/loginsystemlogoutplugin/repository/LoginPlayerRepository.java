package pl.pomoku.loginsystemlogoutplugin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.loginsystemlogoutplugin.entity.LoginPlayer;

public interface LoginPlayerRepository extends JpaRepository<LoginPlayer, Long> {
    LoginPlayer findByPlayerUUID(String playerUUID);
}
