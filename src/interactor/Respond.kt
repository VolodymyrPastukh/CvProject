package com.vovan.interactor

import com.vovan.models.CvData

sealed class Respond

data class GetAllRespond(
    val data: List<CvData>,
    val type: CvData.Type
): Respond()

data class GetOneRespond(
    val data: CvData,
    val type: CvData.Type
): Respond()

data class PostRespond(
    val title: String,
    val isAdded: Boolean
): Respond()
