package nl.naxanria.showdown.command;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

import nl.naxanria.showdown.BoundingBox;
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
			return "&cMake a selection first!";

		BoundingBox boundingBox = new BoundingBox(selection);

		arenaHandler.setBoundingBox(boundingBox);
		arenaHandler.setWorld(executor.getWorld().getName());
		boundingBox.saveConfig(configuration, "arena");
		configuration.setConfigValue("arena.world", executor.getWorld().getName());
		configuration.save();

		return "&3Saved the arena";
	}

	public final ArenaHandler arenaHandler;
	public final IConfiguration configuration;
	public final WorldEditPlugin worldEdit;

}
