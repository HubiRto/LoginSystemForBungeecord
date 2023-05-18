package pl.pomoku.loginsystemlogoutplugin.service;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.springframework.stereotype.Service;
import pl.pomoku.loginsystemlogoutplugin.entity.LoginPlayer;
import pl.pomoku.loginsystemlogoutplugin.repository.LoginPlayerRepository;

@Service("loginPlayerService")
@RequiredArgsConstructor
public class LoginPlayerService {
    private final LoginPlayerRepository loginPlayerRepository;

    public void update(LoginPlayer loginPlayer) {
        loginPlayerRepository.save(loginPlayer);
    }

    public LoginPlayer findByPlayer(String UUID) {
        return loginPlayerRepository.findByPlayerUUID(UUID);
    }

    public LoginPlayer findByPlayer(ProxiedPlayer player){
        return findByPlayer(player.getUniqueId().toString());
    }
}
