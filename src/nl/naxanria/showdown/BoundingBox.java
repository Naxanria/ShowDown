package nl.naxanria.showdown;

import com.sk89q.worldedit.bukkit.selections.Selection;
import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.RunsafeWorld;

public class BoundingBox
{

	public BoundingBox(double x, double y, double z, double w, double h, double l) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.h = h;
		this.l = l;
	}

	public BoundingBox(Selection selection)
	{
				x = selection.getMinimumPoint().getX();
				y = selection.getMinimumPoint().getY();
				z = selection.getMinimumPoint().getZ();
				w = (double) selection.getWidth();
				h = (double) selection.getHeight();
				l = (double) selection.getLength();
	}

	public boolean contains(RunsafeLocation location)
	{
		return contains(location.getX(), location.getY(), location.getZ());
	}

	public void saveConfig(IConfiguration config, String base)
	{

		config.setConfigValue(base + ".x", x);
		config.setConfigValue(base + ".y", y);
		config.setConfigValue(base + ".z", z);
		config.setConfigValue(base + ".w", w);
		config.setConfigValue(base + ".h", h);
		config.setConfigValue(base + ".l", l);

		config.save();

	}

	public boolean contains(double x_, double z_)
	{
		return ((x_ >= x && x_ <= x + w) && (z_ >= z && z_ <= z + l));
	}

	public boolean contains(double x_, double y_, double z_)
	{
		return ((x_ >= x && x_ <= x + w) && (y_ >= y && y_ <= y + h) && (z_ >= z && z_ <= z + l));
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getZ()
	{
		return z;
	}

	public void setZ(double z)
	{
		this.z = z;
	}

	public double getW()
	{
		return w;
	}

	public void setW(double w)
	{
		this.w = w;
	}

	public double getH()
	{
		return h;
	}

	public void setH(double h)
	{
		this.h = h;
	}

	public double getL()
	{
		return l;
	}

	public void setL(double l)
	{
		this.l = l;
	}

	public RunsafeLocation middle(RunsafeWorld world)
	{
		return new RunsafeLocation(world,
				x + (x + w) / 2,
				y + (y + h) / 2,
				z + (z + l) / 2
			);
	}

	private double x;
	private double y;
	private double z;
	private double w;
	private double h;
	private double l;

}
