package com.rr.tvstarterkit.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.rr.tvstarterkit.base.listener.BackHandlerInterface
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class TvBaseFragment:Fragment() {
    protected var mActivity: TvBaseActivity? = null
    protected var mBackHandlerInterface: BackHandlerInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is TvBaseActivity) {
            val activity = context as TvBaseActivity?
            this.mActivity = activity
            mBackHandlerInterface = getActivity() as BackHandlerInterface?
        }
    }

}