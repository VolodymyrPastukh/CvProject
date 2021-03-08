package com.vovan.models

import kotlinx.serialization.Serializable

@Serializable
sealed class CvData{
    abstract val title: String
    abstract val type: Type

    enum class Type{
        COMPLEX,
        PET,
        TECHNOLOGY
    }
}

@Serializable
data class Project(
    override val title: String,
    val description: String,
    val gitLink: String,
    override val type: Type = Type.PET,
    val technologies: List<Technology>
): CvData()

@Serializable
data class ComplexProject(
    override val title: String,
    val description: String,
    val gitLink: String,
    override val type: Type = Type.COMPLEX,
    val projects: List<Project>
): CvData()

@Serializable
data class Technology(
    override val title: String,
    val tool: Tool = Tool.LANGUAGE,
    override val type: Type = Type.TECHNOLOGY
): CvData()
{
    enum class Tool{
        LANGUAGE,
        DATABASE,
        LIBRARY,
        FRAMEWORK,
        DEVOPS,
        SERVICE
    }
}
