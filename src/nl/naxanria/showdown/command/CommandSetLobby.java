package nl.naxanria.showdown.command;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import javafx.geometry.BoundingBox;
import nl.naxanria.showdown.handlers.ArenaHandler;
import nl.naxanria.showdown.handlers.LobbyHandler;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class CommandSetLobby extends PlayerCommand
{


	public CommandSetLobby(IConfiguration configuration, LobbyHandler lobbyHandler)
	{
		super("setlobby", "set the arena", "showdown.admin.arena");
		this.lobbyHandler = lobbyHandler;
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

		lobbyHandler.setLobby(
				new BoundingBox(x, y, z, w, l, h)
		);
		lobbyHandler.setWorld(executor.getWorld().getName());

		configuration.setConfigValue("lobby.x", x);
		configuration.setConfigValue("lobby.y", y);
		configuration.setConfigValue("lobby.z", z);
		configuration.setConfigValue("lobby.w", w);
		configuration.setConfigValue("lobby.h", h);
		configuration.setConfigValue("lobby.l", l);
		configuration.setConfigValue("lobby.world", executor.getWorld().getName());
		configuration.save();

		return "&cSaved the arena";
	}

	public final LobbyHandler lobbyHandler;
	public final IConfiguration configuration;
	public final WorldEditPlugin worldEdit;

}
