package pl.pomoku.loginsystemlogoutplugin;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import org.reflections.Reflections;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.pomoku.loginsystemlogoutplugin.service.LoginPlayerService;
import pl.pomoku.loginsystemlogoutplugin.service.SessionService;

import java.lang.reflect.InvocationTargetException;

public final class LoginSystemLogoutPlugin extends Plugin {
    private static AnnotationConfigApplicationContext applicationContext;
    public static LoginPlayerService loginPlayerService;
    public static LoginSystemLogoutPlugin plugin;
    public static SessionService sessionService;

    @Override
    public void onEnable() {
        plugin = this;

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("pl.pomoku.loginsystemlogoutplugin");
        applicationContext.refresh();

        loginPlayerService = (LoginPlayerService) applicationContext.getBean("loginPlayerService");
        sessionService = (SessionService) applicationContext.getBean("sessionService");
        loadListenersAndCommands();
    }

    private void loadListenersAndCommands() {
        String packageName = getClass().getPackage().getName();
        for (Class<?> clazz : new Reflections(packageName + ".listeners").getSubTypesOf(Listener.class)) {
            try {
                Listener listener = (Listener) clazz.getDeclaredConstructor().newInstance();
                getProxy().getPluginManager().registerListener(this, listener);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
