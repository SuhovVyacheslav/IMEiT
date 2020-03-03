package ru.suhov.student.view.ftagments.maps

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fr_tab.*
import ru.suhov.student.base.BasePagerFragment
import ru.suhov.student.view.adapters.MapPagerAdapter

class MapsPagerFragment : BasePagerFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = MapPagerAdapter(childFragmentManager)
        tabLayout.post { tabLayout.setupWithViewPager(viewPager) }
    }
}