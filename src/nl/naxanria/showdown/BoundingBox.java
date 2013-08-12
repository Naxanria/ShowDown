package nl.naxanria.showdown;

import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.RunsafeWorld;

public class BoundingBox
{

	public BoundingBox(double x, double y, double z, double w, double h, double d) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.h = h;
		this.d = d;
	}

	public boolean contains(RunsafeLocation location)
	{
		return contains(location.getX(), location.getY(), location.getZ());
	}

	public boolean contains(double x_, double z_)
	{
		return ((x_ >= x && x_ <= x + w) && (z_ >= z && z_ <= z + d));
	}

	public boolean contains(double x_, double y_, double z_)
	{
		return ((x_ >= x && x_ <= x + w) && (y_ >= y && y_ <= y + h) && (z_ >= z && z_ <= z + d));
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

	public double getD()
	{
		return d;
	}

	public void setD(double d)
	{
		this.d = d;
	}

	public RunsafeLocation middle(RunsafeWorld world)
	{
		return new RunsafeLocation(world,
				x + (x + w) / 2,
				y + (y + h) / 2,
				z + (z + d) / 2
			);
	}

	private double x;
	private double y;
	private double z;
	private double w;
	private double h;
	private double d;

}
