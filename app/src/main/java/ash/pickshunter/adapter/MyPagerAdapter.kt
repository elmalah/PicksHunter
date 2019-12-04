package ash.pickshunter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ash.pickshunter.fragment.RequestsDoneFragment
import ash.pickshunter.fragment.RequestsPendingFragment
import ash.pickshunter.fragment.RequestsRejectedFragment


class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                RequestsDoneFragment()
            }
            1 -> RequestsPendingFragment()
            else -> {
                return RequestsRejectedFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Done"
            1 -> "Pending"
            else -> {
                return "Rejected"
            }
        }
    }

}