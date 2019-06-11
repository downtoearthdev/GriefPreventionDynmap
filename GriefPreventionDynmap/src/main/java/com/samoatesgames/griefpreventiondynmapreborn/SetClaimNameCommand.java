package com.samoatesgames.griefpreventiondynmapreborn;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetClaimNameCommand implements CommandExecutor {
    private GriefPrevention gpPlugin;

    public SetClaimNameCommand(GriefPrevention gpPlugin) {
        this.gpPlugin = gpPlugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player sender = (Player)commandSender;
            for(Claim claim : GriefPreventionDynmap.claimsList) {
                if(claim.contains(sender.getLocation(), true, false)) {
                    GriefPreventionDynmap.loadedNames.put(claim.getID().toString(), String.join(" ", strings));
                    sender.sendMessage(ChatColor.AQUA + "Set DynMap record of claim to " + String.join(" ", strings) + "!");
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "You are not standing inside a valid claim!");
            return true;
        }
        return false;
    }
}
