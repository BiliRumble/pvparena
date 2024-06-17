package net.slipcor.pvparena.listeners;

import net.slipcor.pvparena.api.PVPArenaAPI;
import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.arena.ArenaPlayer;
import net.slipcor.pvparena.commands.PAA_Edit;
import net.slipcor.pvparena.core.Config;
import net.slipcor.pvparena.core.Language;
import net.slipcor.pvparena.loadables.ArenaModule;
import net.slipcor.pvparena.managers.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class QuitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.teleport(Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        // handle quit event
        Player p = event.getPlayer();

        String ArenaName = PVPArenaAPI.getArenaName(p);

        if (ArenaName == null) {
            return;
        }

        Arena arena = ArenaManager.getArenaByName(ArenaName);
        final ArenaPlayer aPlayer = ArenaPlayer.parsePlayer(p.getName());

        if (arena.isValid()) {
            arena.callLeaveEvent(aPlayer.get());
            arena.playerLeave(aPlayer.get(), Config.CFG.TP_EXIT, false, false, false);
        }
    }
}
