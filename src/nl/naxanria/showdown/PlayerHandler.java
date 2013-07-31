package nl.naxanria.showdown;

import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.ArrayList;

public class PlayerHandler
{


	public boolean addPlayerToQue(RunsafePlayer player)
	{
		if(inQue.contains(player.getName()))
			return false;
		inQue.add(player.getName());
		return true;
	}

	public boolean removePlayerFromQue(RunsafePlayer player)
	{
		if(!inQue.contains(player.getName()))
			return false;
		inQue.remove(player.getName());
		return true;
	}

	public void moveQueIntoGame()
	{
		if(inQue.size() < minSize)
			return;
		inGame.addAll(inQue);
		inQue.clear();
		//todo: teleport + gear

	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public RunsafeLocation getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(RunsafeLocation endLocation) {
		this.endLocation = endLocation;
	}

	public RunsafeLocation getStartLocaion() {
		return startLocaion;
	}

	public void setStartLocaion(RunsafeLocation startLocaion) {
		this.startLocaion = startLocaion;
	}

	private ArrayList<String> inGame;
	private ArrayList<String> inQue;
	private RunsafeLocation startLocaion;
	private RunsafeLocation endLocation;
	private int minSize = 2;

}
