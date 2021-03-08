package repository.factories

import com.mongodb.client.MongoCollection
import com.vovan.models.CvData

abstract class CvDataFactory<T: CvData>(open val db: MongoCollection<T>) {
    abstract fun getAll(): List<T>
    abstract fun getOne(query: String): T
    abstract fun putOne(element: T): Boolean
}