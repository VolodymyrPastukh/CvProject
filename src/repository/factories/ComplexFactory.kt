package repository.factories

import com.mongodb.client.MongoCollection
import com.vovan.models.ComplexProject
import com.vovan.models.CvData
import com.vovan.models.Project
import com.vovan.models.Technology
import exceptions.AbsentElementException
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.setValue

class ComplexFactory(
    override val db: MongoCollection<ComplexProject>,
    private val projectFactory: ProjectFactory
) : CvDataFactory<ComplexProject>(db) {
    override fun getAll(): List<ComplexProject> = db.find().toList()

    override fun getOne(query: String): ComplexProject =
        db.findOne(ComplexProject::title eq query) ?: throw AbsentElementException()


    override fun putOne(element: ComplexProject): Boolean =
        try {
            getOne(element.title)
            false
        } catch (e: AbsentElementException) {
            projectFactory.putList(element.projects)
            db.insertOne(element)
            true
        }

    fun addAdditional(title: String, data: Project): Boolean =
        try {
            val projects = getOne(title).projects.toMutableList().also { it.add(data) }
            projectFactory.putOne(data)
            db.updateOne(Project::title eq title, setValue(ComplexProject::projects, projects))
            true
        } catch (e: AbsentElementException) {
            false
        }


}