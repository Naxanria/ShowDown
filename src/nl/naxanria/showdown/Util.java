package nl.naxanria.showdown;

import javafx.geometry.BoundingBox;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.RunsafeWorld;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.List;

public class Util
{


	public static boolean isPointInBoundingBox(RunsafeLocation location, BoundingBox boundingBox)
	{
		return boundingBox.contains(location.getX(), location.getY(), location.getZ());
	}

	public static void sendMessage(List<RunsafePlayer> players, String msg)
	{
		for (RunsafePlayer player : players)
			player.sendColouredMessage(msg);
	}

	public static RunsafeLocation getMiddleLocation(BoundingBox boundingBox, RunsafeWorld world) {

		double middleX = (boundingBox.getMaxX() - boundingBox.getMinX()) / 2;
		double middleY = (boundingBox.getMaxY() - boundingBox.getMinY()) / 2;
		double middleZ = (boundingBox.getMaxZ() - boundingBox.getMinZ()) / 2;

		return new RunsafeLocation(world, middleX, middleY, middleZ);
	}
}
