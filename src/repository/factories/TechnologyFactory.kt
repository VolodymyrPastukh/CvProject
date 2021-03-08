package repository.factories

import com.mongodb.client.MongoCollection
import com.vovan.models.CvData
import com.vovan.models.Technology
import exceptions.AbsentElementException
import org.litote.kmongo.eq
import org.litote.kmongo.findOne

class TechnologyFactory(override val db: MongoCollection<Technology>) : CvDataFactory<Technology>(db) {
    override fun getAll() = db.find().toList()

    override fun getOne(query: String) =
        db.findOne(Technology::title eq query) ?: throw AbsentElementException()

    override fun putOne(element: Technology): Boolean =
        try {
            getOne(element.title)
            false
        } catch (e: AbsentElementException) {
            db.insertOne(element)
            true
        }

    fun putList(list: List<Technology>) = list.forEach { putOne(it) }

}