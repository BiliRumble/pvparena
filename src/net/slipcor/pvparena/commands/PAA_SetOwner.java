package net.slipcor.pvparena.commands;

import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.core.Config.CFG;
import net.slipcor.pvparena.core.Help;
import net.slipcor.pvparena.core.Help.HELP;
import net.slipcor.pvparena.core.Language;
import net.slipcor.pvparena.core.Language.MSG;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>PVP Arena SETOWNER Command class</pre>
 * <p/>
 * A command to set an arena owner
 *
 * @author slipcor
 * @version v0.10.0
 */

public class PAA_SetOwner extends AbstractArenaCommand {

    public PAA_SetOwner() {
        super(new String[]{"pvparena.cmd.setowner"});
    }

    @Override
    public void commit(final Arena arena, final CommandSender sender, final String[] args) {
        if (!this.hasPerms(sender, arena)) {
            return;
        }

        if (!argCountValid(sender, arena, args, new Integer[]{1})) {
            return;
        }

        //                                   args[0]
        // usage: /pa {arenaname} setowner [playername]

        arena.setOwner(args[0]);
        arena.getArenaConfig().set(CFG.GENERAL_OWNER, args[0]);
        arena.getArenaConfig().save();
        arena.msg(sender, Language.parse(arena, MSG.SETOWNER_DONE, args[0], arena.getName()));
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public void displayHelp(final CommandSender sender) {
        Arena.pmsg(sender, Help.parse(HELP.SETOWNER));
    }

    @Override
    public List<String> getMain() {
        return Arrays.asList("setowner");
    }

    @Override
    public List<String> getShort() {
        return Arrays.asList("!so");
    }

    @Override
    public CommandTree<String> getSubs(final Arena arena) {
        CommandTree<String> result = new CommandTree<String>(null);
        result.define(new String[]{"%server%"});
        return result;
    }
}
