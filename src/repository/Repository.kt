package repository

import com.vovan.models.CvData

interface Repository {
    fun findAllData(type: CvData.Type): List<CvData>
    fun findOneData(title: String, type: CvData.Type): CvData

    fun addAdditional(destination: String, data: CvData): Boolean

    fun putData(data: CvData): Boolean
}