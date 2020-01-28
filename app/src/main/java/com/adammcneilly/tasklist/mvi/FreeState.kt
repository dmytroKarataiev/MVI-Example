package com.adammcneilly.tasklist.mvi

import android.os.Parcelable
import com.adammcneilly.tasklist.base.BaseState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FreeState(val task: TaskState? = null, val ad: AdState? = null) : BaseState, Parcelable

