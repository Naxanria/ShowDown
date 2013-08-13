package nl.naxanria.showdown.command;

import nl.naxanria.showdown.handlers.PlayerHandler;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class CommandSetEndLocation extends PlayerCommand
{

	public CommandSetEndLocation(PlayerHandler playerHandler, IConfiguration configuration)
	{
		super("endlocation", "Defines the end location", "showdown.admin");
		this.playerHandler = playerHandler;
		this.configuration = configuration;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{

		RunsafeLocation location = executor.getLocation();

		playerHandler.setEndLocation(location);

		configuration.setConfigValue("end.x", location.getX());
		configuration.setConfigValue("end.y", location.getY());
		configuration.setConfigValue("end.z", location.getZ());
		configuration.save();

		return "&3Set the location to: &f" + executor.getLocation().toString();
	}

	private final PlayerHandler playerHandler;
	private final IConfiguration configuration;

}
