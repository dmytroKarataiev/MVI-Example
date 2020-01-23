package com.adammcneilly.tasklist.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ad(
    val description: String
) : Parcelable