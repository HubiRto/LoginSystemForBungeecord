package pl.pomoku.loginsystemlogoutplugin.service;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.springframework.stereotype.Service;
import pl.pomoku.loginsystemlogoutplugin.entity.LoginPlayer;
import pl.pomoku.loginsystemlogoutplugin.entity.Session;
import pl.pomoku.loginsystemlogoutplugin.repository.SessionRepository;

import java.util.UUID;

import static pl.pomoku.loginsystemlogoutplugin.LoginSystemLogoutPlugin.loginPlayerService;

@Service("sessionService")
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public void create(Session session) {
        sessionRepository.save(session);
    }
    public void update(Session session){
        create(session);
    }

    public Session findByPlayer(LoginPlayer loginPlayer) {
        return sessionRepository.findByPlayer(loginPlayer);
    }

    public Session findByPlayer(ProxiedPlayer player) {
        UUID playerUUID = player.getUniqueId();
        LoginPlayer loginPlayer = loginPlayerService.findByPlayer(playerUUID.toString());
        if (loginPlayer == null) return null;
        return findByPlayer(loginPlayer);
    }
}
