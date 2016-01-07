package net.slipcor.pvparena.events;

import net.slipcor.pvparena.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * PVP Arena Win Event class
 * </pre>
 * <p/>
 * is called when a player wins an arena match
 *
 * @author slipcor
 * @version v0.9.1
 */

public class PAWinEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Arena arena;
    private final Player player;
    private final List<String> items;

    public PAWinEvent(final Arena arena, final Player player, final String[] arrItems) {
        super();
        this.arena = arena;
        this.player = player;
        items = new ArrayList<>();

        if (arrItems == null || arrItems.length == 0) {
            return;
        }
        items.addAll(Arrays.asList(arrItems));
    }

    public void addItemString(final String item) {
        items.add(item);
    }

    public Arena getArena() {
        return arena;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public String[] getItems() {
        final String[] output = new String[items.size()];
        int pos = 0;
        for (final String s : items) {
            output[pos++] = s;
        }
        return output;
    }

    public Player getPlayer() {
        return player;
    }

    public void removeItemString(final String item) {
        items.remove(item);
    }
}
