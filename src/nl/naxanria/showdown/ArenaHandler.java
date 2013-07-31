package nl.naxanria.showdown;

import javafx.geometry.BoundingBox;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.minecraft.RunsafeLocation;

public class ArenaHandler implements IConfigurationChanged
{

	public boolean isPositionInArena(RunsafeLocation location)
	{
		return boundingBox != null && location.getWorld().getName().equalsIgnoreCase(world)
			&& Util.isPointInBoundingBox(location, boundingBox);
	}

	public void setBoundingBox(BoundingBox boundingBox)
	{
		this.boundingBox = boundingBox;
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		this.setBoundingBox(
				new BoundingBox(
						configuration.getConfigValueAsDouble("arena.x"),
						configuration.getConfigValueAsDouble("arena.y"),
						configuration.getConfigValueAsDouble("arena.z"),
						configuration.getConfigValueAsDouble("arena.w"),
						configuration.getConfigValueAsDouble("arena.h"),
						configuration.getConfigValueAsDouble("arena.d")
				)
		);
		this.world = configuration.getConfigValueAsString("world");
	}


	private BoundingBox boundingBox;
	private String world = "showdown";
}
