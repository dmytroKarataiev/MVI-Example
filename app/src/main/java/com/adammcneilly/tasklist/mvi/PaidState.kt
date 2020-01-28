package com.adammcneilly.tasklist.mvi

import android.os.Parcelable
import com.adammcneilly.tasklist.base.BaseState
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaidState(val task: TaskState? = null) : BaseState, Parcelable
