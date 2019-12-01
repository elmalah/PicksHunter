package ash.pickshunter

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_trip.*
import com.google.android.material.floatingactionbutton.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_trip_details.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TripFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [TripFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TripFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var trip: Trip? = null
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip, container, false)
    }
    private fun initRecycler(){
        recyclerView = rv_parent as RecyclerView

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                RecyclerView.HORIZONTAL, false)
            adapter = ChildAdapter(ChildDataFactory
                .getChildren(40))
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        fb_actions.setOnClickListener()
        {
            NavHostFragment.findNavController(main_navigation).navigate(R.id.fragment_trip_stores)

            //toggleButton(fb_add_expense)
            //toggleButton(fb_add_product)
            //toggleButton(fb_end_trip)
            //toggleButton(fb_checkin_store)

            //toggleText(text_add_expense)
            //toggleText(text_add_product)
            //toggleText(text_end_trip)
            //toggleText(text_checkin_store)
        }

      // fb_checkin_store.setOnClickListener(){
      //     NavHostFragment.findNavController(navigation_trip).navigate(R.id.fragment_trip_stores)
      // }
    }

    fun toggleButton(button: ExtendedFloatingActionButton) {
        if (button.isShown()) {
            button.hide(true);
        } else {
            button.show(true);
        }
    }

    fun toggleText(textView: TextView) {

        if (textView.visibility == View.VISIBLE) {
            textView.visibility = View.INVISIBLE
        } else {
            textView.visibility = View.VISIBLE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TripFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TripFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
object ChildDataFactory{

    private val random = Random()

    private val titles =  arrayListOf( "Aviator", "Now you can See me", "God Father", "Mission Impossible", "3 idiots")

    private fun randomTitle() : String{
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomImage() : Int{
        return R.drawable.hunter
    }

    fun getChildren(count : Int) : List<ChildModel>{
        val children = mutableListOf<ChildModel>()
        repeat(count){
            val child = ChildModel(randomImage(), randomTitle())
            children.add(child)
        }
        return children
    }


}