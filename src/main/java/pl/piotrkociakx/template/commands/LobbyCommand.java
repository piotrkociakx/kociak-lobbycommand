package pl.piotrkociakx.template.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import pl.piotrkociakx.template.helpers.ChatHelper;
import pl.piotrkociakx.template.conifg.ConfigManager;

public class LobbyCommand extends Command {

    public String lobbyserver;
    public String lobbyteleportmessage;

    public LobbyCommand(Plugin plugin, ConfigManager configManager) {
        super("lobby");
        lobbyserver = configManager.getConfig().getString("lobbyserver");
        lobbyteleportmessage = configManager.getConfig().getString("lobbysuccesteleportmessage");
        plugin.getProxy().getPluginManager().registerCommand(plugin, this);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof net.md_5.bungee.api.connection.ProxiedPlayer)) {
            sender.sendMessage("Ta komenda wymaga bycia graczem!");
            return;
        }

        net.md_5.bungee.api.connection.ProxiedPlayer player = (net.md_5.bungee.api.connection.ProxiedPlayer) sender;

        ServerInfo lobbyServer = ProxyServer.getInstance().getServerInfo(lobbyserver);
        if (lobbyServer != null) {
            if (!player.getServer().getInfo().getName().equalsIgnoreCase(lobbyserver)) {
                player.connect(lobbyServer);
                player.sendMessage(ChatHelper.colored(lobbyteleportmessage));
            } else {
                player.sendMessage(ChatHelper.colored("&cJesteś już na lobby"));
            }
        } else {
            player.sendMessage(ChatHelper.colored("&cNie udało się przeteleportować na serwer lobby, czy serwer istnieje lub jest wlaczony?"));
        }
    }
}
