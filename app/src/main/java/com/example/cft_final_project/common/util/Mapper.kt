package com.example.cft_final_project.common.util

interface Mapper<I, O> {
    fun map(input: I): O
}

/** @return лист с отмапленными объектами либо пустой лист, если входящий был null */
fun <I, O> Mapper<I, O>.mapListOrEmpty(inputList: List<I>?): List<O> {
    return inputList?.map { map(it) }.orEmpty()
}