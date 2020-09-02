package com.shardbytes.safearea

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import org.jetbrains.annotations.TestOnly

data class Area(
    var start: Vector,
    var end: Vector,
    var dimension: String
) {

    init {
        val newStart = Vector.getMinimum(start, end)
        val newEnd = Vector.getMaximum(start, end)

        start = newStart
        end = newEnd

    }

    companion object {
        fun fromString(string: String): Area {
            val coords = mutableListOf<Int>()
            val splitString = string.split(" ")

            //Get vectors
            splitString.dropLast(1).forEach { coords.add(it.toInt()) }

            //Get dimension
            val dim = splitString.last()

            return Area(Vector(coords[0], coords[1], coords[2]), Vector(coords[3], coords[4], coords[5]), dim)

        }

        fun fromString(string: Array<out String>, dimension: String): Area {
            val coords = mutableListOf<Int>()
            string.forEach { coords.add(it.toInt()) }
            return Area(Vector(coords[0], coords[1], coords[2]), Vector(coords[3], coords[4], coords[5]), dimension)

        }

    }

    fun locationInside(location: Location): Boolean {
        return location.x >= start.x && location.x <= end.x && location.y >= start.y && location.y <= end.y && location.z >= start.z && location.z <= end.z && location.world.name == dimension

    }

    override fun toString(): String {
        return "${start.blockX} ${start.blockY} ${start.blockZ} ${end.blockX} ${end.blockY} ${end.blockZ} ${dimension}"

    }

}