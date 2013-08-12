package nl.naxanria.showdown.events;

import nl.naxanria.showdown.Core;
import nl.naxanria.showdown.handlers.ArenaHandler;
import nl.naxanria.showdown.handlers.PlayerHandler;
import no.runsafe.framework.api.event.player.IPlayerMove;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

public class PlayerMove implements IPlayerMove
{

	public PlayerMove(Core core, PlayerHandler playerHandler, ArenaHandler arenaHandler)
	{
		this.core = core;
		this.playerHandler = playerHandler;
		this.arenaHandler = arenaHandler;
	}

	@Override
	public boolean OnPlayerMove(RunsafePlayer player, RunsafeLocation from, RunsafeLocation to) {

		if (playerHandler.isInGame(player))
		{
			player.setSaturation(20f);
			player.setFoodLevel(20);
			if(!arenaHandler.isPositionInArena(to))
			{
				playerHandler.removePlayer(player);
				player.teleport(playerHandler.getEndLocation());
				if(playerHandler.getAmountPlayers() == 1)
					core.winner();

			}
			else if(!playerHandler.isInGame(player) && arenaHandler.isPositionInArena(to))
			{
				if(!player.isCreative())
					player.teleport(playerHandler.getEndLocation());
			}
		}

		return true;
	}

	private final Core core;
	private final PlayerHandler playerHandler;
	private final ArenaHandler arenaHandler;


}
