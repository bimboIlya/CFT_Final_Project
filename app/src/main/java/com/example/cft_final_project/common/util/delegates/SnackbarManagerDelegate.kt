package com.example.cft_final_project.common.util.delegates

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.cft_final_project.common.presentation.SnackbarManager
import com.example.cft_final_project.common.presentation.SnackbarManagerImpl
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Делагат, создающий [SnackbarManager] в onViewCreated() фрагмента и обнуляющий его в onViewDestroyed()
 * @throws IllegalStateException при обращении к переменной в onDestroyView(), до OnViewCreated()
 */
class SnackbarManagerDelegate(
    fragment: Fragment,
    anchorId: Int?
) : ReadWriteProperty<Fragment, SnackbarManager> {
    private var snackbarManager: SnackbarManager? = null

    init {
        fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
            viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                override fun onCreate(owner: LifecycleOwner) {
                    snackbarManager = SnackbarManagerImpl(fragment.requireView(), anchorId)
                }

                override fun onDestroy(owner: LifecycleOwner) {
                    snackbarManager = null
                }
            })
        }
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: SnackbarManager) {
        snackbarManager = value
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): SnackbarManager {
        return snackbarManager ?: throw IllegalStateException(
            "Attempted to call SnackbarManager after OnDestroyView() or before onViewCreated()"
        )
    }
}

fun Fragment.snackbarManager(@IdRes anchorId: Int? = null) =
    SnackbarManagerDelegate(this, anchorId)