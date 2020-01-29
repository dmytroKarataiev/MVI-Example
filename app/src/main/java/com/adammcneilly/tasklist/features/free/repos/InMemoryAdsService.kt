package com.adammcneilly.tasklist.features.free.repos

import com.adammcneilly.tasklist.data.Ad
import com.adammcneilly.tasklist.features.free.repos.AdsRepository

class InMemoryAdsService :
    AdsRepository {

    override fun getAd(): Ad {
        return Ad("Test ad")
    }

}