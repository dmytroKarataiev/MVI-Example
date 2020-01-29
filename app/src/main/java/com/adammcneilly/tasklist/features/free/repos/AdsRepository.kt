package com.adammcneilly.tasklist.features.free.repos

import com.adammcneilly.tasklist.data.Ad

interface AdsRepository {
    fun getAd(): Ad
}