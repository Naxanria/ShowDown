package nl.naxanria.showdown;

import nl.naxanria.showdown.handlers.*;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.IOutput;
import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.List;


public class Core implements IConfigurationChanged, IPluginEnabled
{

	public Core(ArenaHandler arenaHandler, LobbyHandler lobbyHandler, PlayerHandler playerHandler, IOutput console, IScheduler scheduler, EquipmentHandler equipmentHandler,
							AnnouncementAreaHandler announcementAreaHandler)
	{
		this.arenaHandler = arenaHandler;
		this.lobbyHandler = lobbyHandler;
		this.playerHandler = playerHandler;
		this.console = console;
		this.scheduler = scheduler;
		this.equipmentHandler = equipmentHandler;
		this.announcementHandler = announcementAreaHandler;

	}


	public void countDownToStart()
	{
		if(countDownStarted)
			return;

		countDownStarted = true;
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

		int[] times = new int[]{5, 3, 2, 1};

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
				Util.sendMessage(playerHandler.getIngamePlayers(), "&3Fight!");
			}
		}, delay);
		this.timers.add(id);

	}

	private void startInToDamage(int i)
	{
		Util.sendMessage(playerHandler.getIngamePlayers(), String.format("&3The fight starts in &f%d!", i));
	}

	public void startInTime(int time)
	{
		announcementHandler.sendAnnouncement(String.format("&3Showdown will start in &f%d&3 seconds!", time));
	}

	public void start()
	{
		countDownStarted = false;
		List<RunsafePlayer> players = lobbyHandler.getLobbyPlayers();
		if(players.size() >= playerHandler.getMinSize())
		{
			this.gameStarted = true;
			this.damagable = false;
			playerHandler.addPlayers(players);
			equipmentHandler.equipAll(players);
			countDownToDamage();
			console.fine("Enough players! minSize: %d, actual size: %d ", playerHandler.getMinSize(), players.size());
			playerHandler.teleportAllToStart();
		}
		else {
			announcementHandler.sendAnnouncement("&3Not enough players");
			console.fine("Not enough players!");
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
		countDownStarted = false;
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{

	}

	@Override
	public void OnPluginEnabled()
	{
		countDownToStart();
	}

	public void winner()
	{
		RunsafeServer.Instance.broadcastMessage(String.format("%s &3was victorious in ShowDown!", playerHandler.getIngamePlayers().get(0).getPrettyName()));
		playerHandler.teleportAllToEnd();
		playerHandler.clear();
		stop();
		countDownToStart();
	}



	private final ArenaHandler arenaHandler;
	private final LobbyHandler lobbyHandler;
	private final PlayerHandler playerHandler;
	private final EquipmentHandler equipmentHandler;
	private final IOutput console;
	private final IScheduler scheduler;
	private final AnnouncementAreaHandler announcementHandler;


	private boolean countDownStarted = false;
	private boolean gameStarted = false;
	private boolean damagable = false;
	private ArrayList<Integer> timers = new ArrayList<Integer>();


}
