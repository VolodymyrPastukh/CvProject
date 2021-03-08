package com.vovan.interactor

import com.vovan.models.CvData

sealed class Query() {
    class QueryAllByType(val type: CvData.Type) : Query()
    class QueryDataByTitle(val title: String, val type: CvData.Type): Query()
    class QueryAddData(val data: CvData): Query()
    class QueryAddAdditionalData(val data: CvData, val destination: String): Query()
}

