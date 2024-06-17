package net.slipcor.pvparena.expanisons;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.slipcor.pvparena.PVPArena;
import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.core.Language;
import net.slipcor.pvparena.managers.ArenaManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArenaExpanion extends PlaceholderExpansion {
    private final PVPArena plugin;

    public ArenaExpanion(PVPArena plugin) {
        this.plugin = plugin;
    }
    @Override
    public @NotNull String getIdentifier() {
        return "pvparena";
    }

    @Override
    public @NotNull String getAuthor() {
        return "rumble";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.startsWith("status_")) {
            String arenaName = params.replace("status_", "");
            Arena arena = ArenaManager.getArenaByName(arenaName);
            if (arena.isFree()) {
                return Language.parse(Language.MSG.PLACEHOLDER_STATUS_FREE);
            }
            return Language.parse(Language.MSG.PLACEHOLDER_STATUS_VALID);
        }

        if (params.startsWith("startcount_")) {
            String arenaName = params.replace("startcount_", "");
            Arena arena = ArenaManager.getArenaByName(arenaName);
            return String.valueOf(arena.getStartCount());
        }

        if (params.startsWith("playercount_")) {
            String arenaName = params.replace("playercount_", "");
            Arena arena = ArenaManager.getArenaByName(arenaName);
            Number playerCount = arena.getEveryone().size();
            return playerCount.toString();
        }

        return null;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.startsWith("status_")) {
            String arenaName = params.replace("status_", "");
            Arena arena = ArenaManager.getArenaByName(arenaName);
            if (arena.isFree()) {
                return Language.parse(Language.MSG.PLACEHOLDER_STATUS_FREE);
            }
            return Language.parse(Language.MSG.PLACEHOLDER_STATUS_VALID);
        }

        if (params.startsWith("startcount_")) {
            String arenaName = params.replace("startcount_", "");
            Arena arena = ArenaManager.getArenaByName(arenaName);
            return String.valueOf(arena.getStartCount());
        }

        if (params.startsWith("playercount_")) {
            String arenaName = params.replace("playercount_", "");
            Arena arena = ArenaManager.getArenaByName(arenaName);
            Number playerCount = arena.getEveryone().size();
            return playerCount.toString();
        }

        return null;
    }

}
