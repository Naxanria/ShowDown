package nl.naxanria.showdown.events;

import nl.naxanria.showdown.Core;
import nl.naxanria.showdown.handlers.PlayerHandler;
import no.runsafe.framework.api.event.player.IPlayerQuitEvent;
import no.runsafe.framework.minecraft.event.player.RunsafePlayerQuitEvent;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

public class PlayerDisconnectEvent implements IPlayerQuitEvent
{

	public PlayerDisconnectEvent(PlayerHandler playerHandler, Core core)
	{
		this.playerHandler = playerHandler;
		this.core = core;
	}

	@Override
	public void OnPlayerQuit(RunsafePlayerQuitEvent event)
	{
		RunsafePlayer player = event.getPlayer();
		if(playerHandler.isInGame(player))
		{
			playerHandler.removePlayer(player);
			if (playerHandler.getAmountPlayers() == 1)
				core.winner();
		}

	}

	private final PlayerHandler playerHandler;
	private final Core core;

}
