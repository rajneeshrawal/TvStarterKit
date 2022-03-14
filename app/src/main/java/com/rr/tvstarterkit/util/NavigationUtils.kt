package com.rr.tvstarterkit.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object NavigationUtils {

    /**
     * method to inflate the fragment
     */
    fun inflateFragment(iFragmentToInflate: Fragment, iFragmentManager: FragmentManager, iContentFrameId: Int, isAddToBackStack: Boolean?, isReplace: Boolean, isAllowCommitLoss: Boolean = false) {
        val transaction = iFragmentManager.beginTransaction()
        val tag = iFragmentToInflate::class.simpleName

        if (isReplace) {
            transaction.replace(iContentFrameId, iFragmentToInflate, tag)
        } else {
            transaction.add(iContentFrameId, iFragmentToInflate, tag)
        }
        if (isAddToBackStack == true) {
            transaction.addToBackStack(tag)
        }
        if (isAllowCommitLoss) {
            transaction.commitAllowingStateLoss()
        } else {
            transaction.commit()
        }
    }


}