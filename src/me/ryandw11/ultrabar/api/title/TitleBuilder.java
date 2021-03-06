package me.ryandw11.ultrabar.api.title;

import me.ryandw11.ultrabar.UltraBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * This class allows for Title information to be stored dynamically.
 * This class is primarily used by the TitleCommand.
 */
public class TitleBuilder {
    private String title;
    private String subTitle;
    private UltraBar plugin;
    private List<Player> players;
    private int fadeIn;
    private int fadeOut;
    private int time;
    public TitleBuilder(UltraBar plugin){
        this.title = "";
        this.subTitle = "";
        this.plugin = plugin;
        this.players = new ArrayList<>();
        this.fadeIn = 5;
        this.fadeOut = 5;
        this.time = 10;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void addAllPlayers(){
        players.addAll(Bukkit.getOnlinePlayers());
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public void setTime(int time){
        this.time = time;
    }

    public boolean hasPlayers(){
        return !players.isEmpty();
    }

    /**
     * Send the title to the players.
     * <p>The players list is cleared to prevent memory leaks.</p>
     */
    public void send(){
        for(Player p : players){
            plugin.mgr.title(plugin.papi.getMessage(UltraBar.plugin.chatColorUtil.translateChatColor(this.title), p), p,
                    fadeIn, time, fadeOut, plugin.papi.getMessage(plugin.chatColorUtil.translateChatColor(this.subTitle), p));
        }
        players.clear();
    }
}
