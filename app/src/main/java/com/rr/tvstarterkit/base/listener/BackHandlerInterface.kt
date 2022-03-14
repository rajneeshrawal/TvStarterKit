package com.rr.tvstarterkit.base.listener

import androidx.fragment.app.Fragment

interface BackHandlerInterface {
    fun setSelectedFragment(fragment: Fragment,id:String)
}