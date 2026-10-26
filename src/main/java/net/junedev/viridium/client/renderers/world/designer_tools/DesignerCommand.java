package net.junedev.viridium.client.renderers.world.designer_tools;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;

public class DesignerCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "designer";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/designer [on|off]";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        boolean on;
        if (args.length == 0) {
            on = !DesignerRegistry.active;
        } else if (args[0].equalsIgnoreCase("on")) {
            on = true;
        } else if (args[0].equalsIgnoreCase("off")) {
            on = false;
        } else {
            throw new WrongUsageException(getCommandUsage(sender));
        }

        DesignerRegistry.setActive(on);
        sender.addChatMessage(new ChatComponentText("Designer " + (on ? "enabled" : "disabled")));
    }

    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return args.length == 1 ? getListOfStringsMatchingLastWord(args, "on", "off") : null;
    }
}
