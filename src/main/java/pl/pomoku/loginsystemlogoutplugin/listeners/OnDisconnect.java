package pl.pomoku.loginsystemlogoutplugin.listeners;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.pomoku.loginsystemlogoutplugin.entity.LoginPlayer;
import pl.pomoku.loginsystemlogoutplugin.entity.Session;

import java.net.InetSocketAddress;

import static pl.pomoku.loginsystemlogoutplugin.LoginSystemLogoutPlugin.loginPlayerService;
import static pl.pomoku.loginsystemlogoutplugin.LoginSystemLogoutPlugin.sessionService;

public class OnDisconnect implements Listener {
    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        updateLogoutPlayerStatus(player);
        updatePlayerIP(player);
    }

    private static void updateLogoutPlayerStatus(ProxiedPlayer player) {
        LoginPlayer loginPlayer = loginPlayerService.findByPlayer(player);
        if (loginPlayer == null) return;
        loginPlayer.setLogged(false);
        loginPlayerService.update(loginPlayer);
    }

    private static void updatePlayerIP(ProxiedPlayer player) {
        Session session = sessionService.findByPlayer(player);
        if (session == null) return;
        session.setLastIP(getProxiedPlayerIP(player));
        sessionService.update(session);
    }

    private static String getProxiedPlayerIP(ProxiedPlayer player) {
        InetSocketAddress address = player.getAddress();
        if(address == null) return null;
        return address.getAddress().getHostAddress();
    }
}
