package repository

import com.vovan.models.ComplexProject
import com.vovan.models.Project
import com.vovan.models.Technology
import com.vovan.models.CvData
import repository.factories.ComplexFactory
import repository.factories.ProjectFactory
import repository.factories.TechnologyFactory


class RepositoryDBImpls(
    private val technologyFactory: TechnologyFactory,
    private val projectFactory: ProjectFactory,
    private val complexFactory: ComplexFactory
) : Repository {
    override fun findAllData(type: CvData.Type): List<CvData> =
        when (type) {
            CvData.Type.TECHNOLOGY -> technologyFactory.getAll()
            CvData.Type.PET -> projectFactory.getAll()
            CvData.Type.COMPLEX -> complexFactory.getAll()
        }

    override fun findOneData(title: String, type: CvData.Type): CvData =
        when (type) {
            CvData.Type.TECHNOLOGY -> technologyFactory.getOne(title)
            CvData.Type.PET -> projectFactory.getOne(title)
            CvData.Type.COMPLEX -> complexFactory.getOne(title)
        }

    override fun putData(data: CvData): Boolean =
        when (data.type) {
            CvData.Type.TECHNOLOGY -> technologyFactory.putOne(data as Technology)
            CvData.Type.PET -> projectFactory.putOne(data as Project)
            CvData.Type.COMPLEX -> complexFactory.putOne(data as ComplexProject)
        }

    override fun addAdditional(destination: String, data: CvData): Boolean =
        when (data.type) {
            CvData.Type.TECHNOLOGY -> projectFactory.addAdditional(destination ,data as Technology)
            CvData.Type.PET -> complexFactory.addAdditional(destination, data as Project)
            else -> false
        }

}

/*    fun <T: CvData, F: CvDataFactory<T>> selector(type: CvData.Type, block: F.() -> T) {
        when (type) {
            CvData.Type.TECHNOLOGY -> block
            CvData.Type.PET -> technologyFactory.getAll()
            CvData.Type.COMPLEX -> technologyFactory.getAll()
        }
    }*/



