package ash.pickshunter.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager.widget.ViewPager
import ash.pickshunter.adapter.ProductAdapter
import ash.pickshunter.model.ProductView
import ash.pickshunter.R
import ash.pickshunter.adapter.MyProfileTabAdapter
import ash.pickshunter.adapter.TimelineTabAdapter
import ash.pickshunter.viewModel.TripViewModel
import ash.pickshunter.utils.InjectorUtils
import ash.pickshunter.utils.PreferenceHelper
import ash.pickshunter.utils.ProgressDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_profile.*
import kotlinx.android.synthetic.main.fragment_time_line.*

class TimeLineFragment : Fragment() {

    var fragmentAdapter: TimelineTabAdapter? = null

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_line, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentAdapter = TimelineTabAdapter(::onRefreshListener, childFragmentManager)
        viewpager_timeline.adapter = fragmentAdapter
        tabs_timeline.setupWithViewPager(viewpager_timeline)
        viewpager_timeline.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                loadData()
            }

        })

        loadData()
    }

    fun onRefreshListener() {
        loadData()
    }


    fun loadData() {
        ProgressDialog.show(requireContext(), false)
        fragmentAdapter!!.notifyChange(arrayListOf(), arrayListOf(), arrayListOf())

        fragmentAdapter!!.showLoader(0)
        fragmentAdapter!!.showLoader(1)
        fragmentAdapter!!.showLoader(2)
        viewModel.getTimelineProduct(PreferenceHelper(requireContext()).user.id).observe(this) {

            val _liveList: ArrayList<ProductView> = it.live
            val _followingList: ArrayList<ProductView> = it.following
            val _trendingList: ArrayList<ProductView> = it.trending

            _liveList.forEach() {
                it.productName = "_liveList"
            }

            _followingList.forEach() {
                it.productName = "_followingList"
            }

            _trendingList.forEach() {
                it.productName = "_trendingList"
            }

            fragmentAdapter!!.notifyChange(_liveList, _followingList, _trendingList)
            ProgressDialog.dismiss()
        }
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

    }
}
