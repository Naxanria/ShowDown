package nl.naxanria.showdown.command;

import nl.naxanria.showdown.Core;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class CommandStop extends PlayerCommand
{

	public CommandStop(Core core)
	{
		super("stop", "stops the game", "showdown.admin");
		this.core = core;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{

		core.stop();

		return null;
	}

	private final Core core;


}
