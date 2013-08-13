package nl.naxanria.showdown;

import nl.naxanria.showdown.command.*;
import nl.naxanria.showdown.events.*;
import nl.naxanria.showdown.handlers.*;
import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.framework.api.command.Command;

public class Plugin extends RunsafeConfigurablePlugin
{
	@Override
	protected void PluginSetup()
	{

		this.addComponent(ArenaHandler.class);
		this.addComponent(LobbyHandler.class);
		this.addComponent(PlayerHandler.class);
		this.addComponent(EquipmentHandler.class);
		this.addComponent(AnnouncementAreaHandler.class);

		this.addComponent(Core.class);

		this.addComponent(PlayerDamage.class);
		this.addComponent(PlayerMove.class);
		this.addComponent(PlayerDeath.class);
		this.addComponent(PlayerDisconnectEvent.class);

		Command command = new Command("showdown", "showdown commands", null);
		command.addSubCommand(this.getInstance(CommandSetArena.class));
		command.addSubCommand(this.getInstance(CommandSetLobby.class));
		command.addSubCommand(this.getInstance(CommandStart.class));
		command.addSubCommand(this.getInstance(CommandStop.class));
		command.addSubCommand(this.getInstance(CommandSetStartLocation.class));
		command.addSubCommand(this.getInstance(CommandSetEndLocation.class));
		command.addSubCommand(this.getInstance(CommandSetAnnouncementArea.class));

		this.addComponent(command);



	}
}
