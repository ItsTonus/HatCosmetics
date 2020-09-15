package me.Tonus_.hatCosmetics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HatCosmeticTab implements TabCompleter {
    List<String> arguments = new ArrayList<>();
    Set<String> hats = Main.hats.keySet();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(arguments.isEmpty()) {
            arguments.add("help"); arguments.add("equip"); arguments.add("unequip"); arguments.add("reload");
        }

        List<String> result = new ArrayList<>();
        if(args.length == 1) {
            for(String a : arguments) {
                if(a.toLowerCase().startsWith(args[0].toLowerCase())) result.add(a);
            }
            return result;
        }
        if(args.length > 1) {
            if(args[0].equalsIgnoreCase("equip")) {
                if(args.length == 2) {
                    for(String a : hats) {
                        if(a.toLowerCase().startsWith(args[1].toLowerCase())) result.add(a);
                    }
                    return result;
                }
            }
        }

        return null;
    }
}
