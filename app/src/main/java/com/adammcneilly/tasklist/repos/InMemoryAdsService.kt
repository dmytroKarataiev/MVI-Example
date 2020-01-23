package com.adammcneilly.tasklist.repos

import com.adammcneilly.tasklist.data.Ad

class InMemoryAdsService : AdsRepository {

    override fun getAd(): Ad {
        return Ad("Test ad")
    }

}