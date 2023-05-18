package pl.pomoku.loginsystemlogoutplugin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pomoku.loginsystemlogoutplugin.entity.LoginPlayer;
import pl.pomoku.loginsystemlogoutplugin.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Session findByPlayer(LoginPlayer player);
}
