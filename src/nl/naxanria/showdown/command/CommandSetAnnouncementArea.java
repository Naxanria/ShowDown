package nl.naxanria.showdown.command;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import nl.naxanria.showdown.BoundingBox;
import nl.naxanria.showdown.handlers.AnnouncementAreaHandler;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class CommandSetAnnouncementArea extends PlayerCommand
{

	public CommandSetAnnouncementArea(IConfiguration configuration, AnnouncementAreaHandler announcementAreaHandler)
	{
		super("announcementarea","Sets the announcements area","showdown.admin");

		this.configuration = configuration;
		this.announcementAreaHandler = announcementAreaHandler;

		worldEdit = RunsafeServer.Instance.getPlugin("WorldEdit");

	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		Selection selection = worldEdit.getSelection(executor.getRawPlayer());
		if(selection == null)
			return "&cPlease select an area.";

		announcementAreaHandler.setAnnouncementArea(
				new BoundingBox(
						selection.getMinimumPoint().getX(),
						selection.getMinimumPoint().getY(),
						selection.getMinimumPoint().getZ(),
						selection.getWidth(),
						selection.getHeight(),
						selection.getLength()
				)
		);

		configuration.setConfigValue("announcement.x", selection.getMinimumPoint().getX());
		configuration.setConfigValue("announcement.y", selection.getMinimumPoint().getY());
		configuration.setConfigValue("announcement.z", selection.getMinimumPoint().getZ());
		configuration.setConfigValue("announcement.w", selection.getWidth());
		configuration.setConfigValue("announcement.h", selection.getHeight());
		configuration.setConfigValue("announcement.l", selection.getLength());

		configuration.save();


		return "&cSaved the announcement area.";
	}

	private final IConfiguration configuration;
	private final AnnouncementAreaHandler announcementAreaHandler;
	private final WorldEditPlugin worldEdit;
}
