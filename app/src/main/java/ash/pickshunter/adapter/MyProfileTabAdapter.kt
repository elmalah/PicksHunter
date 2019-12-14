package ash.pickshunter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ash.pickshunter.fragment.PlanTripFragment
import ash.pickshunter.fragment.ProductListTabFragment
import ash.pickshunter.fragment.RequestsListTabFragment


class MyProfileTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

//        return when (position) {
//            0 -> {
//                ProductListTabFragment()
//            }
//            1 -> ProductListTabFragment()
//
//            else -> {
//                return ProductListTabFragment()
//            }
//        }
        return PlanTripFragment()
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Followers"
            1 -> "Trips"
            else -> {
                return "Products"
            }
        }
    }

}