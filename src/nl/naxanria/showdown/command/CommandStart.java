package nl.naxanria.showdown.command;

import nl.naxanria.showdown.Core;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class CommandStart extends PlayerCommand
{

	public CommandStart(Core core)
	{
		super("start", "Starts the countdown", "showdown.admin");
		this.core = core;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{

		if (!core.isGameStarted())
			core.countDownToStart();

		return null;
	}

	private final Core core;

}
