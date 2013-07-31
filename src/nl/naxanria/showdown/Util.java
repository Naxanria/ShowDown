package nl.naxanria.showdown;

import javafx.geometry.BoundingBox;
import no.runsafe.framework.minecraft.RunsafeLocation;

public class Util
{


	public static boolean isPointInBoundingBox(RunsafeLocation location, BoundingBox boundingBox)
	{
		return boundingBox.contains(location.getX(), location.getY(), location.getZ());
	}

}
