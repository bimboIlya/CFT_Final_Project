package com.example.cft_final_project.common.util.delegates

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Ленивый делагат, присваивающий своему свойству значение null при вызове onDestroyView() фрагмента
 * @throws IllegalStateException при обращении к переменной в onDestroyView(), до OnViewCreated()
 */
open class AutoClearedValue<T : Any>(fragment: Fragment) : ReadWriteProperty<Fragment, T> {
    var value: T? = null

    init {
        // когда viewLifecycle становится доступен,
        // цепляет на него лисенер, обнуляющий value на onDestroyView
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    value = null
                }
            })
        }
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        this.value = value
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return value ?: throw IllegalStateException(
            "Attempted to call AutoClearedValue after OnDestroyView() or before onViewCreated()"
        )
    }
}

fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)