package ash.pickshunter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import ash.pickshunter.fragment.RequestsListTabFragment


class RequestsListTabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

//Unknown = 0,
//PendingApproval = 1,
//Purchased = 2,
//DeliveredToCustomer = 3,
//Rejected = 4,
//Canceled = 5

        return when (position) {
            0 -> {
                RequestsListTabFragment(1)
            }
            1 -> RequestsListTabFragment(2)
            else -> {
                return RequestsListTabFragment(4)
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Pending"
            1 -> "Done"
            else -> {
                return "Rejected"
            }
        }
    }

}