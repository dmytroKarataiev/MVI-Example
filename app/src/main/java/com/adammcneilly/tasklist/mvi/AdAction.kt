package com.adammcneilly.tasklist.mvi

import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.data.Ad

sealed class AdAction : BaseAction {

    object AdLoading : AdAction()
    class AdsLoaded(val ad: Ad) : AdAction()
    class AdErrored(e: Throwable) : AdAction()

}