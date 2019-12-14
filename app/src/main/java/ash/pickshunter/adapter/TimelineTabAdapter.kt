package ash.pickshunter.adapter

import android.content.Context
import androidx.fragment.app.*
import ash.pickshunter.fragment.ProductListTabFragment
import ash.pickshunter.fragment.RequestsListTabFragment
import ash.pickshunter.model.ProductView
import ash.pickshunter.utils.InjectorUtils
import ash.pickshunter.viewModel.TripViewModel
import kotlinx.android.synthetic.main.fragment_product_list_tab.*
import android.os.Bundle


class TimelineTabAdapter(
    val onRefreshListener: () -> Unit,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var _liveList: ArrayList<ProductView> = arrayListOf()
    private var _followingList: ArrayList<ProductView> = arrayListOf()
    private var _trendingList: ArrayList<ProductView> = arrayListOf()


    fun notifyChange(
        liveList: ArrayList<ProductView>,
        followingList: ArrayList<ProductView>,
        trendingList: ArrayList<ProductView>
    ) {
        _liveList = liveList
        _followingList = followingList
        _trendingList = trendingList

        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {

        var productFragment = ProductListTabFragment(onRefreshListener)

        val args = Bundle()
        args.putInt("tabIndex", position)
        productFragment.arguments = args

        return productFragment
    }

    override fun getItemPosition(`object`: Any): Int {
        if (`object` is ProductListTabFragment) {

            var productFragment = (`object` as ProductListTabFragment)

            var position = productFragment.arguments!!.getInt("tabIndex")

            when (position) {
                0 -> {
                    productFragment.loadData(_liveList)
                }
                1 -> {
                    productFragment.loadData(_followingList)
                }
                2 -> {
                    productFragment.loadData(_trendingList)
                }
            }
        }

        return super.getItemPosition(`object`)
    }

    fun showLoader(position: Int){
        var productFragment = (getItem(position) as ProductListTabFragment)

        productFragment.showLoader()
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Live"
            1 -> "Following"
            else -> {
                return "Trending"
            }
        }
    }

}