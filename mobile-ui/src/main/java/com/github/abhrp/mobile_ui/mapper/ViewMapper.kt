package com.github.abhrp.mobile_ui.mapper

interface ViewMapper<in P, out V> {
    fun mapToView(presentationModel: P): V
}