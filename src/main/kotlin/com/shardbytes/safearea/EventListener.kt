package com.shardbytes.safearea

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Monster
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason.*

object EventListener: Listener {

    @EventHandler
    @Suppress("unused")
    fun mobSpawnHandler(event: CreatureSpawnEvent) {
        if(event.spawnReason == NATURAL || event.spawnReason == TRAP || event.spawnReason == PATROL) { // Allow spawn eggs
            if(event.entity is Monster) { // Allow neutral or passive mobs
                if(Areas.isInsideAnArea(event.location) != null) {
                    event.isCancelled = true

                }

            }

        }

    }

}