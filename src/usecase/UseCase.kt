package usecase

import com.jetbrains.handson.httpapi.exceptions.IllegalEnteredDataException
import com.vovan.interactor.Query
import com.vovan.interactor.Respond
import com.vovan.interactor.SearchEngine
import com.vovan.models.CvData
import com.vovan.models.Technology

class UseCase(private val searchEngine: SearchEngine) {

    fun getRequest(type: String): Respond {
        val query = Query.QueryAllByType(toCvDataType(type.trim()))
        return searchEngine.request(query)
    }

    fun getRequest(type: String, title: String): Respond {
        val query = Query.QueryDataByTitle(title, toCvDataType(type))
        return searchEngine.request(query)
    }

    fun postRequest(input: CvData): Respond{
        val query = input.toQuery()
        return searchEngine.request(query)
    }

    fun postRequest(input: CvData, destination: String): Respond{
        val query = input.toQuery(destination)
        return searchEngine.request(query)
    }

    private fun CvData.toQuery() = Query.QueryAddData(this)
    private fun CvData.toQuery(destination: String) = Query.QueryAddAdditionalData(this, destination)


    private fun toCvDataType(type: String): CvData.Type =
        when (type) {
            PET -> CvData.Type.PET
            COMPLEX -> CvData.Type.COMPLEX
            TECHNOLOGIES -> CvData.Type.TECHNOLOGY
            else -> throw IllegalEnteredDataException()
        }


    companion object {
        const val COMPLEX = "complexes"
        const val PET = "projects"
        const val TECHNOLOGIES = "technologies"
    }

}

