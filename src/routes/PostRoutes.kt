package routes


import com.jetbrains.handson.httpapi.DependencyProvider
import com.jetbrains.handson.httpapi.exceptions.IllegalEnteredDataException
import com.vovan.interactor.GetAllRespond
import com.vovan.interactor.PostRespond
import com.vovan.models.ComplexProject
import com.vovan.models.Project
import com.vovan.models.Technology
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.mustache.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.registerPostRoutes(){
    routing {
        postTechnology()
        postProject()
        postComplex()
    }
}

private fun Route.postTechnology(){
    post("/addTech"){
        val params = call.receiveParameters()
        val project = params["project"].toString()

        val projectsRespond = postcase.getRequest("projects") as GetAllRespond
        val listProjects = projectsRespond.data.map { it.title }
        val technology = Technology(
            title = params["title"].toString(),
            tool = params["tool"].toString().toTool(),
        )

        val respond = postcase.postRequest(technology, project) as PostRespond
        if(respond.isAdded) call.respond(MustacheContent("addTech.hbs", mapOf("projects" to listProjects))) else
            call.respond(MustacheContent("error.hbs", null))

    }

    static("static"){
        resources("css")
    }
    static("pictures"){
        resources("files")
    }
}

private fun Route.postProject(){
    post("/addProject"){
        val params = call.receiveParameters()
        val complex = params["complex"].toString()

        val project = Project(
            title = params["title"].toString(),
            description = params["description"].toString(),
            gitLink = params["gitLink"].toString(),
            technologies = emptyList()
        )

        val complexRespond = navcase.getRequest("complexes") as GetAllRespond
        val listComplex = complexRespond.data.map { it.title }
        val respond = postcase.postRequest(project, complex) as PostRespond
        if(respond.isAdded) call.respond(MustacheContent("addProject.hbs", mapOf("complexes" to listComplex))) else
            call.respond(MustacheContent("error.hbs", null))

    }

    static("static"){
        resources("css")
    }
    static("pictures"){
        resources("files")
    }
}

private fun Route.postComplex(){
    post("/addComplex"){
        val params = call.receiveParameters()
        val project = ComplexProject(
            title = params["title"].toString(),
            description = params["description"].toString(),
            gitLink = params["gitLink"].toString(),
            projects = emptyList()
        )
        val respond = postcase.postRequest(project) as PostRespond
        if(respond.isAdded) call.respond(MustacheContent("addComplex.hbs", null)) else
            call.respond(MustacheContent("error.hbs", null))

    }

    static("static"){
        resources("css")
    }
    static("pictures"){
        resources("files")
    }
}


val postcase = DependencyProvider.usecase


fun String.toTool(): Technology.Tool =
    when(this){
        Technology.Tool.LANGUAGE.toString() -> Technology.Tool.LANGUAGE
        Technology.Tool.DATABASE.toString() -> Technology.Tool.DATABASE
        Technology.Tool.LIBRARY.toString() -> Technology.Tool.LIBRARY
        Technology.Tool.FRAMEWORK.toString() -> Technology.Tool.FRAMEWORK
        Technology.Tool.DEVOPS.toString() -> Technology.Tool.DEVOPS
        Technology.Tool.SERVICE.toString() -> Technology.Tool.SERVICE
        else -> throw IllegalEnteredDataException()
    }
