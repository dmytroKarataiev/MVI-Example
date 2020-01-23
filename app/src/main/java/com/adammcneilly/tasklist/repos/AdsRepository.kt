package com.adammcneilly.tasklist.repos

import com.adammcneilly.tasklist.data.Ad

interface AdsRepository {
    fun getAd(): Ad
}