package nl.naxanria.showdown.events;

import nl.naxanria.showdown.Core;
import nl.naxanria.showdown.handlers.PlayerHandler;
import no.runsafe.framework.api.event.player.IPlayerDamageEvent;
import no.runsafe.framework.minecraft.event.entity.RunsafeEntityDamageEvent;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.Effect;

public class PlayerDamage implements IPlayerDamageEvent
{

	public PlayerDamage(Core core, PlayerHandler playerHandler)
	{
		this.core = core;
		this.playerHandler = playerHandler;
	}

	@Override
	public void OnPlayerDamage(RunsafePlayer player, RunsafeEntityDamageEvent event)
	{
		if(playerHandler.isInGame(player))
		{
			if(!core.isDamagable())
				event.cancel();
			else
			{
				player.getWorld().playEffect(player.getLocation(), Effect.getById(2001), 152);
			}
		}
	}

	private final Core core;
	private final PlayerHandler playerHandler;
}
