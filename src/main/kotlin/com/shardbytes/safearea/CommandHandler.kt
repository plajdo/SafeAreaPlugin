package com.shardbytes.safearea

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object CommandHandler {

    fun handleCommand(sender: CommandSender, command: Command, args: Array<out String>): Boolean {
        if(command.name.equals("sa-test", true)) {
            return pluginTest(sender)

        }
        if(command.name.equals("sa-check", ignoreCase = true)) {
            return checkArea(sender)

        }
        if(command.name.equals("sa-create", true)) {
            return createSafeArea(sender, args)

        }
        if(command.name.equals("sa-remove", true)) {
            return removeSafeArea(sender)

        }
        if(command.name.equals("sa-list", true)) {
            return listSafeAreas(sender)

        }
        return false

    }

    private fun pluginTest(sender: CommandSender): Boolean {
        sender.sendMessage(ChatColor.GREEN.toString() + "Plugin installed and loaded succesfully.")
        return true

    }

    private fun createSafeArea(sender: CommandSender, args: Array<out String>): Boolean {
        if(sender is Player) {
            if(args.size > 6) {
                sender.sendMessage(ChatColor.RED.toString() + "Too many arguments.")
                return false

            }
            if(args.size < 6) {
                sender.sendMessage(ChatColor.RED.toString() + "Not enough arguments.")
                return false

            }

            val result = Areas.add(args, sender.world.name)
            if (result) {
                sender.sendMessage(ChatColor.GREEN.toString() + "Safe area created.")
                return true

            } else {
                sender.sendMessage(ChatColor.RED.toString() + "Could not create safe area.") //TODO: Check for overlapping areas?
                return false

            }

        }
        return false

    }

    private fun removeSafeArea(sender: CommandSender): Boolean {
        if(sender is Player) {
            val result = Areas.remove(sender.location)
            if(result) {
                sender.sendMessage(ChatColor.GREEN.toString() + "Safe area removed.")
                return true

            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You must stand inside a safe area to remove it.")
                return true

            }

        }
        return false

    }

    private fun listSafeAreas(sender: CommandSender): Boolean {
        sender.sendMessage(ChatColor.GREEN.toString() + "List of all safe areas:")
        Areas.list.forEach {
            sender.sendMessage(it.toString())

        }
        return true

    }

    private fun checkArea(sender: CommandSender): Boolean {
        if(sender is Player) {
            val currentArea = Areas.isInsideAnArea(sender.location)
            if(currentArea != null) {
                sender.sendMessage(ChatColor.GREEN.toString() + "Yes" + ChatColor.RESET.toString() + ", you are standing inside a safe area.")
                sender.sendMessage("Current safe area bounds: " + currentArea.toString())
                return true

            } else {
                sender.sendMessage(ChatColor.RED.toString() + "No" + ChatColor.RESET.toString() + ", you are not standing inside a safe area.")
                return true

            }

        }
        return false

    }

}