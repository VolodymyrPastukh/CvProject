package com.jetbrains.handson.httpapi

import com.vovan.interactor.Query
import com.vovan.interactor.SearchEngine
import com.vovan.models.CvData
import com.vovan.models.Project
import com.vovan.models.Technology
import repository.RepositoryDBImpls
import repository.factories.ComplexFactory
import repository.factories.ProjectFactory
import repository.factories.TechnologyFactory
import usecase.UseCase


object DependencyProvider {
    private val technologyFactory = TechnologyFactory(DatabaseFactory.technologies)
    private val projectFactory = ProjectFactory(DatabaseFactory.projects, technologyFactory)
    private val complexFactory = ComplexFactory(DatabaseFactory.complexProjects, projectFactory)

    private val repository: RepositoryDBImpls = RepositoryDBImpls(technologyFactory, projectFactory, complexFactory)
    private val searchEngine: SearchEngine = SearchEngine(repository)
    val usecase = UseCase(searchEngine)

}
