package com.vovan.interactor

import repository.Repository


class SearchEngine(
    private val repository: Repository
) {
    fun request(query: Query): Respond =
        when (query) {
            is Query.QueryAllByType -> GetAllRespond(repository.findAllData(query.type), query.type)
            is Query.QueryDataByTitle -> GetOneRespond(repository.findOneData(query.title, query.type), query.type)
            is Query.QueryAddData -> PostRespond(query.data.title, repository.putData(query.data))
            is Query.QueryAddAdditionalData -> PostRespond(query.destination, repository.addAdditional(query.destination, query.data))
        }
}
