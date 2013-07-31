package nl.naxanria.showdown;

import javafx.geometry.BoundingBox;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.RunsafeWorld;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LobbyHandler implements IConfigurationChanged
{

	public List<RunsafePlayer> getLobbyPlayers()
	{

		RunsafeWorld runsafeWorld = RunsafeServer.Instance.getWorld(world);
		if(runsafeWorld == null)
			return null;
		List<RunsafePlayer> allPlayers = runsafeWorld.getPlayers();
		List<RunsafePlayer> lobbyPlayers = new ArrayList<RunsafePlayer>();
		for (RunsafePlayer player : allPlayers)
			for (BoundingBox box : lobbies)
				if (Util.isPointInBoundingBox(player.getLocation(), box))
					lobbyPlayers.add(player);

		return lobbyPlayers;
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		Map<String, Map<String, String>> lobbiesMap = configuration.getConfigSectionsAsMap("lobbies");
		this.world = configuration.getConfigValueAsString("world");
		lobbies = new ArrayList<BoundingBox>();
		for(String index : lobbiesMap.keySet())
			lobbies.add(
					new BoundingBox(
							Double.valueOf(lobbiesMap.get(index).get("x")),
							Double.valueOf(lobbiesMap.get(index).get("y")),
							Double.valueOf(lobbiesMap.get(index).get("z")),
							Double.valueOf(lobbiesMap.get(index).get("w")),
							Double.valueOf(lobbiesMap.get(index).get("h")),
							Double.valueOf(lobbiesMap.get(index).get("d"))
					)
			);
	}




	private ArrayList<BoundingBox> lobbies;
	private String world = "showdown";

}
