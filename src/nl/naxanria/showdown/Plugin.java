package nl.naxanria.showdown;

import nl.naxanria.showdown.command.CommandSetArena;
import nl.naxanria.showdown.command.CommandSetLobby;
import nl.naxanria.showdown.events.PlayerDamage;
import nl.naxanria.showdown.events.PlayerMove;
import nl.naxanria.showdown.handlers.ArenaHandler;
import nl.naxanria.showdown.handlers.EquipmentHandler;
import nl.naxanria.showdown.handlers.LobbyHandler;
import nl.naxanria.showdown.handlers.PlayerHandler;
import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.api.command.Command;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void PluginSetup()
	{

		this.addComponent(ArenaHandler.class);
		this.addComponent(LobbyHandler.class);
		this.addComponent(PlayerHandler.class);
		this.addComponent(EquipmentHandler.class);

		this.addComponent(Core.class);

		this.addComponent(PlayerDamage.class);
		this.addComponent(PlayerMove.class);

		Command command = new Command("showdown", "showdown commands", null);
		command.addSubCommand(this.getInstance(CommandSetArena.class));
		command.addSubCommand(this.getInstance(CommandSetLobby.class));



	}
}
