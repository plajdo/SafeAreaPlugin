import com.shardbytes.safearea.Area
import com.shardbytes.safearea.Areas
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector
import org.junit.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class KotlinTests {

    @Test
    fun `Areas list toString()`(){
        val areas = mutableListOf(
            Area(Vector(Random.nextInt(), Random.nextInt(), Random.nextInt()), Vector(Random.nextInt(), Random.nextInt(), Random.nextInt()), "end"),
            Area(Vector(Random.nextInt(), Random.nextInt(), Random.nextInt()), Vector(Random.nextInt(), Random.nextInt(), Random.nextInt()), "overworld")
        )
        Areas.list = areas
        val string = Areas.toString()

        Areas.list = mutableListOf()
        Areas.fromString(string)

        assertEquals(areas, Areas.list, "Lists must be equal.")

    }

    @Test
    fun `Area toString()`() {
        val area = Area(Vector(Random.nextInt(), Random.nextInt(), Random.nextInt()), Vector(Random.nextInt(), Random.nextInt(), Random.nextInt()), "nether")
        val string = area.toString()
        val newArea = Area.fromString(string)

        assertEquals(area, newArea, "Area comparison")

    }

}