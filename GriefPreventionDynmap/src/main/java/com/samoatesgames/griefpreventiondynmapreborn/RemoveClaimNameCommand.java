package com.samoatesgames.griefpreventiondynmapreborn;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveClaimNameCommand implements CommandExecutor {

    private GriefPrevention gpPlugin;

    public RemoveClaimNameCommand(GriefPrevention gpPlugin) {
        this.gpPlugin = gpPlugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player sender = (Player)commandSender;
            for(Claim claim : GriefPreventionDynmap.claimsList) {
                if(claim.contains(sender.getLocation(), true, false) && GriefPreventionDynmap.loadedNames.containsKey(claim.getID().toString())) {
                    if(claim.children != null) {
                        for(Claim child : claim.children) {
                            if(child.contains(sender.getLocation(), true, false)) {
                                GriefPreventionDynmap.loadedNames.remove(child.getID().toString());
                                sender.sendMessage(ChatColor.AQUA + "Removed DynMap record of claim!");
                                return true;
                            }
                        }
                    }
                    GriefPreventionDynmap.loadedNames.remove(claim.getID().toString());
                    sender.sendMessage(ChatColor.AQUA + "Removed DynMap record of claim!");
                    return true;
                }
            }
            sender.sendMessage(ChatColor.RED + "You are not standing inside a valid claim!");
            return true;
        }
        return false;
    }
}
