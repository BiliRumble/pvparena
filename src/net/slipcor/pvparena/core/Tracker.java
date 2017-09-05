package net.slipcor.pvparena.core;

import net.slipcor.pvparena.PVPArena;
import net.slipcor.pvparena.core.Language.MSG;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * <pre>Tracker class</pre>
 * <p/>
 * phones home to www.slipcor.net, saving server IP and PVP Arena version
 *
 * @author slipcor
 * @version v0.9.5
 */

public class Tracker implements Runnable {
    private static BukkitTask timerTask;
    private static final Debug debug = new Debug(18);

    /**
     * call home to save the server/plugin state
     */
    private void callHome() {
        if (!PVPArena.instance.getConfig().getBoolean("tracker", true)) {
            stop();
            return;
        }
        debug.i("calling home...");

        String url = null;
        try {
            url = String
                    .format("http://www.slipcor.net/stats/call.php?port=%s&name=%s&version=%s",
                            PVPArena.instance.getServer().getPort(),
                            URLEncoder.encode(PVPArena.instance.getDescription().getName(), "UTF-8"),
                            URLEncoder.encode(PVPArena.instance.getDescription().getVersion(), "UTF-8"));
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            new URL(url).openConnection().getInputStream();
        } catch (final Exception e) {
            PVPArena.instance.getLogger().warning("Error while connecting to www.slipcor.net");
            return;
        }
        debug.i("successfully called home!");
    }

    @Override
    public void run() {
        callHome();
    }

    /**
     * start tracking
     */
    public void start() {
        Language.logInfo(MSG.LOG_TRACKER_ENABLED);

        timerTask = Bukkit.getScheduler().runTaskTimerAsynchronously(PVPArena.instance, this,
                0L, 72000L);
    }

    /**
     * stop tracking
     */
    public static void stop() {
        Language.logInfo(MSG.LOG_TRACKER_DISABLED);
        if (timerTask != null) {
            try {
                timerTask.cancel();
                timerTask = null;
            } catch (Exception e) {

            }
        }
    }
}
