package nl.naxanria.showdown;

import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.List;

public class Util
{
	public static void sendMessage(List<RunsafePlayer> players, String msg)
	{
		for (RunsafePlayer player : players)
			player.sendColouredMessage(msg);
	}
}
