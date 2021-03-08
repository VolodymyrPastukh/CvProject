import com.vovan.models.ComplexProject
import com.vovan.models.Project
import com.vovan.models.Technology
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection


object DatabaseFactory {
    private val client = KMongo.createClient("mongodb://database")
    private val database = client.getDatabase("test")
    val projects = database.getCollection<Project>()
    val complexProjects = database.getCollection<ComplexProject>()
    val technologies = database.getCollection<Technology>()
}
