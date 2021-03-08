package routes


import com.jetbrains.handson.httpapi.DependencyProvider
import com.vovan.interactor.GetAllRespond
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.mustache.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.registerNavigationRoutes(){
    routing {
        navigator()
    }
}

private fun Route.navigator(){
    get("/addTech"){
        val projectsRespond = navcase.getRequest("projects") as GetAllRespond
        val listProjects = projectsRespond.data.map { it.title }
        call.respond(MustacheContent("addTech.hbs", mapOf("projects" to listProjects)))
    }

    get("/addProject"){
        val complexRespond = navcase.getRequest("complexes") as GetAllRespond
        val listComplex = complexRespond.data.map { it.title }
        call.respond(MustacheContent("addProject.hbs", mapOf("complexes" to listComplex)))
    }

    get("/addComplex"){
        call.respond(MustacheContent("addComplex.hbs", null))
    }

    get("/main"){
        call.respond(MustacheContent("index.hbs", null))
    }

    get("/"){
        call.respond(MustacheContent("index.hbs", null))
    }


    static("static"){
        resources("css")
    }
    static("pictures"){
        resources("files")
    }
}

val navcase = DependencyProvider.usecase
