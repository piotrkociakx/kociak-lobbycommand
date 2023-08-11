package pl.piotrkociakx.template;

import net.md_5.bungee.api.plugin.Plugin;

import pl.piotrkociakx.template.commands.LobbyCommand;
import pl.piotrkociakx.template.conifg.ConfigManager;

public final class Main extends Plugin {

    private ConfigManager configManager;
    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        getProxy().getPluginManager().registerCommand(this, new LobbyCommand(this, configManager));

    }

}
