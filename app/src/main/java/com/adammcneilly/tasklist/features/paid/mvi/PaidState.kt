package com.adammcneilly.tasklist.features.paid.mvi

import android.os.Parcelable
import com.adammcneilly.tasklist.base.BaseState
import com.adammcneilly.tasklist.features.tasks.mvi.TaskState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaidState(val task: TaskState? = null) : BaseState, Parcelable
