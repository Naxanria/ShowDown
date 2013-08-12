package nl.naxanria.showdown.handlers;

import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.List;

public class EquipmentHandler
{

	public void equipAll(List<RunsafePlayer> players)
	{
		for(RunsafePlayer player : players)
		{
			equip(player);
		}
	}

	public void equip(RunsafePlayer player)
	{
		player.clearInventory();
	}



}
