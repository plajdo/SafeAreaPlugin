package com.shardbytes.safearea

import org.bukkit.Location
import org.bukkit.util.Vector
import org.jetbrains.annotations.TestOnly

object Areas {

    var list = mutableListOf<Area>()

    fun add(coords: Array<out String>, dimension: String): Boolean {
        try {
            coords.forEach { it.toInt() }

        } catch (e: NumberFormatException) {
            return false

        }

        list.add(Area.fromString(coords, dimension))
        return true

    }

    fun remove(location: Location): Boolean {
        return list.removeIf {
            val start = Vector.getMinimum(it.start, it.end)
            val end = Vector.getMaximum(it.start, it.end)
            location.x >= start.x && location.x <= end.x && location.y >= start.y && location.y <= end.y && location.z >= start.z && location.z <= end.z && location.world.name == it.dimension

        }

    }

    override fun toString(): String {
        val builder = StringBuilder()
        list.forEach {
            builder.append(it.toString())
            builder.append(',')

        }

        //Remove last comma
        builder.deleteCharAt(builder.lastIndex)

        return builder.toString()

    }

    fun isInsideAnArea(location: Location): Area? {
        for(area in list) {
            if(area.locationInside(location)) return area

        }
        return null

    }

    fun fromString(string: String) {
        val areasList = string.split(',')
        areasList.forEach {
            list.add(Area.fromString(it))
        }

    }

}