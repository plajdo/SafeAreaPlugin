package com.shardbytes.safearea

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class SafeArea: JavaPlugin() {

    companion object {
        lateinit var saLogger: java.util.logging.Logger

    }

    override fun onEnable() {
        saLogger = logger

        Bukkit.getServer().pluginManager.registerEvents(EventListener, this)

        try {
            config.load("safearea.config")

            val areaCount = config["areaCount"].toString().toInt()
            logger.log(Level.INFO, "Loading $areaCount area${if(areaCount != 1) "s" else ""}...")

            Areas.fromString(config["areas"].toString())
            assert(Areas.list.size == areaCount)

            logger.log(Level.INFO, "$areaCount area${if(areaCount != 1) "s" else ""} loaded.")

        } catch (e: Exception) {
            logger.log(Level.WARNING, "Config file does not exist. 0 safe areas loaded.")

        }

        super.onEnable()

    }

    override fun onDisable() {
        val areaCount = Areas.list.size
        config["areaCount"] = areaCount

        if(areaCount != 0) {
            config["areas"] = Areas.toString()

        } else {
            config["areas"] = ""

        }
        config.save("safearea.config")

        super.onDisable()

    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return CommandHandler.handleCommand(sender, command, args)

    }

}