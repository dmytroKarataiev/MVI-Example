package com.adammcneilly.tasklist.features.free.mvi

import android.os.Parcelable
import com.adammcneilly.tasklist.base.BaseState
import com.adammcneilly.tasklist.data.Ad
import kotlinx.android.parcel.Parcelize

sealed class AdState : BaseState, Parcelable {

    @Parcelize
    class Loading : AdState()

    @Parcelize
    class Loaded(val ad: Ad) : AdState()

    @Parcelize
    class Error : AdState()

}