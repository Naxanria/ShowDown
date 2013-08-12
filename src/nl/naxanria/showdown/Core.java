package nl.naxanria.showdown;

import nl.naxanria.showdown.handlers.ArenaHandler;
import nl.naxanria.showdown.handlers.LobbyHandler;
import nl.naxanria.showdown.handlers.PlayerHandler;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.List;


public class Core implements IConfigurationChanged
{

	public Core(ArenaHandler arenaHandler, LobbyHandler lobbyHandler, PlayerHandler playerHandler, IOutput console, IScheduler scheduler)
	{
		this.arenaHandler = arenaHandler;
		this.lobbyHandler = lobbyHandler;
		this.playerHandler = playerHandler;
		this.console = console;
		this.scheduler = scheduler;
	}


	public void countDownToStart()
	{

		int[] times = new int[]{30, 15, 10, 5};

		for(final int i : times)
		{
			final int delay = times[0] - i;
			int id = scheduler.startSyncTask(
					new Runnable() {
						@Override
						public void run() {
							startInTime(i);
						}
					}, delay
			);
			this.timers.add(id);
		}

		final int delay = times[0];
		int id = scheduler.startSyncTask(new Runnable() {
			@Override
			public void run() {
				start();

			}
		}, delay);
		this.timers.add(id);

	}

	public void countDownToDamage()
	{

		int[] times = new int[]{10, 7, 5, 3, 2, 1};

		for(final int i : times)
		{
			final int delay = times[0] - i;
			int id = scheduler.startSyncTask(
					new Runnable() {
						@Override
						public void run() {
							startInToDamage(i);
						}
					}, delay
			);
			this.timers.add(id);
		}

		final int delay = times[0];
		int id = scheduler.startSyncTask(new Runnable() {
			@Override
			public void run() {
				setDamagable(true);
				Util.sendMessage(playerHandler.getIngamePlayers(), "&cFight!");
			}
		}, delay);
		this.timers.add(id);

	}

	private void startInToDamage(int i)
	{
		Util.sendMessage(playerHandler.getIngamePlayers(), String.format("&cThe fight starts in %d!", i));
	}

	public void startInTime(int time)
	{
		Util.sendMessage(lobbyHandler.getLobbyPlayers(), String.format("&cShowdown will start in %d seconds!", time));
	}

	public void start()
	{

		List<RunsafePlayer> players = lobbyHandler.getLobbyPlayers();
		if(players.size() > playerHandler.getMinSize())
		{
			this.gameStarted = true;
			this.damagable = false;
			countDownToDamage();
			playerHandler.teleportAllToStart();
		}
		else {
			Util.sendMessage(players, "&cNot enough players");
			countDownToStart();
		}

	}

	public boolean isDamagable()
	{
		return damagable;
	}

	public void setDamagable(boolean damagable)
	{
		this.damagable = damagable;
	}

	public boolean isGameStarted()
	{
		return gameStarted;
	}

	public void stop()
	{

		for(int i : timers)
			scheduler.cancelTask(i);
		timers.clear();
		gameStarted = false;
		damagable = false;
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{

	}

	public void winner()
	{
		RunsafeServer.Instance.broadcastMessage(String.format("%s &cwas victorious in ShowDown!", playerHandler.getIngamePlayers().get(0).getPrettyName()));
	}

	private final ArenaHandler arenaHandler;
	private final LobbyHandler lobbyHandler;
	private final PlayerHandler playerHandler;
	private final IOutput console;
	private final IScheduler scheduler;


	private boolean gameStarted = false;
	private boolean damagable = false;
	private ArrayList<Integer> timers = new ArrayList<Integer>();


}
