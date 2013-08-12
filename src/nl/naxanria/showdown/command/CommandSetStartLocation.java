package nl.naxanria.showdown.command;

import nl.naxanria.showdown.handlers.ArenaHandler;
import nl.naxanria.showdown.handlers.PlayerHandler;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class CommandSetStartLocation extends PlayerCommand
{

	public CommandSetStartLocation(IConfiguration configuration, PlayerHandler playerHandler, ArenaHandler arenaHandler)
	{
		super("startlocation","Sets the start location","showdown.admin");
		this.configuration = configuration;
		this.playerHandler = playerHandler;
		this.arenaHandler = arenaHandler;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{

		if (!arenaHandler.isPositionInArena(executor.getLocation()))
			return "&cThe location needs to be inside the arena";

		RunsafeLocation location = executor.getLocation();

		playerHandler.setStartLocaion(location);
		configuration.setConfigValue("start.x", location.getX());
		configuration.setConfigValue("start.y", location.getY());
		configuration.setConfigValue("start.z", location.getZ());
		configuration.save();

		return "&3Set the location to: &f" + executor.getLocation().toString();
	}

	private final IConfiguration configuration;
	private final PlayerHandler playerHandler;
	private final ArenaHandler arenaHandler;
}
