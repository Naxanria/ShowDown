package nl.naxanria.showdown.handlers;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerHandler implements IConfigurationChanged
{

	public void addPlayers(List<RunsafePlayer> players)
	{
		for(RunsafePlayer player : players)
			addPlayer(player);
	}

	public void addPlayer(RunsafePlayer player)
	{
		inGame.add(player.getName());
	}

	public void removePlayer(RunsafePlayer player)
	{
		inGame.remove(player.getName());
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public RunsafeLocation getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(RunsafeLocation endLocation) {
		this.endLocation = endLocation;
	}

	public RunsafeLocation getStartLocaion() {
		return startLocation;
	}

	public void setStartLocaion(RunsafeLocation startLocation) {
		this.startLocation = startLocation;
	}

	public boolean isInGame(RunsafePlayer player) {
		return inGame.contains(player.getName());
	}

	public List<RunsafePlayer> getIngamePlayers() {

		ArrayList<RunsafePlayer> players = new ArrayList<RunsafePlayer>();

		for (String player : inGame)
			players.add(RunsafeServer.Instance.getPlayerExact(player));

		return players;
	}

	public int getAmountPlayers()
	{
		return inGame.size();
	}

	public void teleportAllToStart() {
		for (String player : inGame)
			RunsafeServer.Instance.getPlayerExact(player).teleport(startLocation);

	}


	public void teleportAllToEnd() {
		for (String player : inGame)
		{
			RunsafePlayer player_ = RunsafeServer.Instance.getPlayerExact(player);

			player_.teleport(endLocation);
			player_.setHealth(20f);
			player_.setFoodLevel(20);
			player_.setSaturation(20f);
		}

	}
	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		minSize = configuration.getConfigValueAsInt("min-players");
		world = configuration.getConfigValueAsString("world");
		startLocation = new RunsafeLocation(
				RunsafeServer.Instance.getWorld(world),
				configuration.getConfigValueAsDouble("start.x"),
				configuration.getConfigValueAsDouble("start.y"),
				configuration.getConfigValueAsDouble("start.z")
			);
		endLocation = new RunsafeLocation(
				RunsafeServer.Instance.getWorld(world),
				configuration.getConfigValueAsDouble("end.x"),
				configuration.getConfigValueAsDouble("end.y"),
				configuration.getConfigValueAsDouble("end.z")
			);

	}
	public void clear()
	{
		inGame.clear();
	}

	private ArrayList<String> inGame = new ArrayList<String>();
	private RunsafeLocation startLocation;
	private RunsafeLocation endLocation;
	private String world = "showdown";
	private int minSize = 2;


}
