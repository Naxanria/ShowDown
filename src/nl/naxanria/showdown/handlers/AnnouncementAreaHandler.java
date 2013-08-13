package nl.naxanria.showdown.handlers;

import nl.naxanria.showdown.BoundingBox;
import nl.naxanria.showdown.Util;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementAreaHandler implements IConfigurationChanged
{


	public void sendAnnouncement(String msg)
	{

		if (announcementArea == null)
			return;

		List<RunsafePlayer> AllPlayers = RunsafeServer.Instance.getWorld(world).getPlayers();
		ArrayList<RunsafePlayer> players = new ArrayList<RunsafePlayer>();
		for(RunsafePlayer player : AllPlayers)
			if(announcementArea.contains(player.getLocation()))
				players.add(player);
		Util.sendMessage(players, msg);
	}

	public void setAnnouncementArea(BoundingBox announcementArea)
	{
		this.announcementArea = announcementArea;
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		this.world = configuration.getConfigValueAsString("world");
		setAnnouncementArea(
				new BoundingBox(
						configuration.getConfigValueAsDouble("announcement.x"),
						configuration.getConfigValueAsDouble("announcement.y"),
						configuration.getConfigValueAsDouble("announcement.z"),
						configuration.getConfigValueAsDouble("announcement.w"),
						configuration.getConfigValueAsDouble("announcement.h"),
						configuration.getConfigValueAsDouble("announcement.l")
				)
		);

	}

	private BoundingBox announcementArea;
	private String world = "showdown";

}
