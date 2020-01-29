package com.adammcneilly.tasklist.features.free.mvi

import android.os.Parcelable
import com.adammcneilly.tasklist.base.BaseState
import com.adammcneilly.tasklist.features.tasks.mvi.TaskState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FreeState(val task: TaskState? = null, val ad: AdState? = null) : BaseState, Parcelable

