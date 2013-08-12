package nl.naxanria.showdown.handlers;

import javafx.geometry.BoundingBox;
import nl.naxanria.showdown.Util;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.RunsafeWorld;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.List;

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
			if (Util.isPointInBoundingBox(player.getLocation(), lobby))
					lobbyPlayers.add(player);

		return lobbyPlayers;
	}

	public void setLobby(BoundingBox lobby)
	{
		this.lobby = lobby;
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		this.world = configuration.getConfigValueAsString("lobby.world");
		setLobby(new BoundingBox(
							configuration.getConfigValueAsDouble("lobby.x"),
							configuration.getConfigValueAsDouble("lobby.y"),
							configuration.getConfigValueAsDouble("lobby.z"),
							configuration.getConfigValueAsDouble("lobby.w"),
							configuration.getConfigValueAsDouble("lobby.h"),
							configuration.getConfigValueAsDouble("lobby.l")
							)
		);
	}

	public void setWorld(String name) {
		world = name;
	}

	public RunsafeLocation getLobbySpawn()
	{

		if(lobbySpawn == null)
			return Util.getMiddleLocation(lobby, RunsafeServer.Instance.getWorld(world));

		return lobbySpawn;
	}

	public void setLobbySpawn(RunsafeLocation lobbySpawn)
	{
		this.lobbySpawn = lobbySpawn;
	}

	private BoundingBox lobby;
	private String world = "showdown";
	private RunsafeLocation lobbySpawn;

}
