package repository.factories

import com.mongodb.client.MongoCollection
import com.vovan.models.CvData
import com.vovan.models.Project
import com.vovan.models.Technology
import exceptions.AbsentElementException
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.setValue

class ProjectFactory(
    override val db: MongoCollection<Project>,
    private val technologyFactory: TechnologyFactory
) : CvDataFactory<Project>(db) {

    override fun getAll(): List<Project> {
        val result = db.find().toList()
        if (result.isEmpty()) throw AbsentElementException()
        return result
    }

    override fun getOne(query: String): Project =
        db.findOne(Project::title eq query) ?: throw AbsentElementException()

    override fun putOne(element: Project): Boolean =
        try {
            getOne(element.title)
            false
        } catch (e: AbsentElementException) {
            db.insertOne(element)
            true
        }


    fun addAdditional(title: String, data: Technology): Boolean =
        try {
            val technologies = getOne(title).technologies.toMutableList().also { it.add(data) }
            technologyFactory.putOne(data)
            db.updateOne(Project::title eq title, setValue(Project::technologies, technologies))
            true
        } catch (e: AbsentElementException) {
            false
        }

    fun putList(list: List<Project>) = list.forEach { putOne(it) }

}

