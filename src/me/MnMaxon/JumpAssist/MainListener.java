package me.MnMaxon.JumpAssist;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MainListener implements Listener {
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getFrom().getY() == e.getTo().getY()
				&& (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ())) {
			Location loc = getTpTo(e.getTo());
			if (loc == null)
				return;
			Boolean sprinting = false;
			if (e.getPlayer().isSprinting())
				sprinting = true;
			e.getPlayer().teleport(loc);
			e.getPlayer().setSprinting(sprinting);
		}
	}

	private Location getTpTo(Location loc) {
		Location finalLoc = null;
		double range = .5;
		if (loc.clone().add(range, 0, 0).getBlock().getType().isSolid())
			finalLoc = loc.clone().add(range, 0, 0);
		else if (loc.clone().add(-range, 0, 0).getBlock().getType().isSolid())
			finalLoc = loc.clone().add(-range, 0, 0);
		else if (loc.clone().add(0, 0, range).getBlock().getType().isSolid())
			finalLoc = loc.clone().add(0, 0, range);
		else if (loc.clone().add(0, 0, -range).getBlock().getType().isSolid())
			finalLoc = loc.clone().add(0, 0, -range);
		if (finalLoc == null || finalLoc.getBlock().getRelative(0, 1, 0).getType().isSolid())
			return null;
		finalLoc = finalLoc.add(0, 1, 0);
		return finalLoc;
	}
}