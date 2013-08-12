package nl.naxanria.showdown.command;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import javafx.geometry.BoundingBox;
import nl.naxanria.showdown.handlers.ArenaHandler;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class CommandSetArena extends PlayerCommand
{


	public CommandSetArena(IConfiguration configuration, ArenaHandler arenaHandler)
	{
		super("setarena", "set the arena", "showdown.admin.arena");
		this.arenaHandler = arenaHandler;
		this.configuration = configuration;
		this.worldEdit = RunsafeServer.Instance.getPlugin("WorldEdit");

	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{

		Selection selection = worldEdit.getSelection(executor.getRawPlayer());
		if(selection == null)
			return "Make a selection first!";

		double x = selection.getMinimumPoint().getX();
		double y = selection.getMinimumPoint().getY();
		double z = selection.getMinimumPoint().getZ();
		double w = selection.getWidth();
		double l = selection.getLength();
		double h = selection.getHeight();

		arenaHandler.setBoundingBox(
				new BoundingBox(x, y, z, w, l, h)
		);
		arenaHandler.setWorld(executor.getWorld().getName());

		configuration.setConfigValue("arena.x", x);
		configuration.setConfigValue("arena.y", y);
		configuration.setConfigValue("arena.z", z);
		configuration.setConfigValue("arena.w", w);
		configuration.setConfigValue("arena.h", h);
		configuration.setConfigValue("arena.l", l);
		configuration.setConfigValue("arena.world", executor.getWorld().getName());
		configuration.save();

		return "&cSaved the arena";
	}

	public final ArenaHandler arenaHandler;
	public final IConfiguration configuration;
	public final WorldEditPlugin worldEdit;

}
