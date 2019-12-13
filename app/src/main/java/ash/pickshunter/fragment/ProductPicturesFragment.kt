package ash.pickshunter.fragment

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe

import ash.pickshunter.R
import ash.pickshunter.adapter.SliderAdapter
import ash.pickshunter.model.PictureRequest
import ash.pickshunter.utils.ProgressDialog
import ash.pickshunter.viewModel.TripViewModel
import ash.pickshunter.utils.InjectorUtils
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_product_pictures.*
import java.io.File
import java.io.FileNotFoundException
import com.coursion.freakycoder.mediapicker.galleries.Gallery
import com.phelat.navigationresult.navigateUp


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProductPicturesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProductPicturesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductPicturesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    var uploadedPictureIds: ArrayList<Int> = arrayListOf()
    var images: ArrayList<String> = arrayListOf()
    var adapter: SliderAdapter? = null

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001
    var image_uri: Uri? = null

    private val viewModel: TripViewModel by viewModels {
        InjectorUtils.provideTripViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        adapter = SliderAdapter(arrayListOf(), true, this::onClickListener)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_pictures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        imageSlider.setSliderAdapter(adapter)
        imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT)
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        imageSlider.isAutoCycle = false

        btn_pick_gallery.setOnClickListener() {
            if (!permissionIfNeeded()) {
                val intent = Intent(requireContext(), Gallery::class.java)
                // Set the title
                intent.putExtra("title", "Select media")
                // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
                intent.putExtra("mode", 2)
                //intent.putExtra("maxSelection", 3) // Optional
                //intent.putExtra("tabBarHidden", true) //Optional - default value is false
                startActivityForResult(intent, OPEN_MEDIA_PICKER)
            }
        }

        fab_camera.setOnClickListener {
            //if system os is Marshmallow or Above, we need to request runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(
                        requireContext(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    == PackageManager.PERMISSION_DENIED
                ) {
                    //permission was not enabled
                    val permission = arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    //show popup to request permission
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    //permission already granted
                    openCamera()
                }
            } else {
                //system os is < marshmallow
                openCamera()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun onClickListener(image: String, position: Int) {
        images.removeAt(position)
        adapter!!.notifyChange(images)
//        if (images.count() > 0)
//            imageSlider.currentPagePosition = 0
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // Inflate the menu items for use in the action bar
        inflater.inflate(R.menu.done_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check which request we're responding to
        if (requestCode == OPEN_MEDIA_PICKER) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK && data != null) {

                val selectionResult = data.getStringArrayListExtra("result")
                selectionResult.forEach {
                    try {
                        Log.d("MyApp", "Image Path : " + it)
                        var file = File(it)
                        val uriFromPath = Uri.fromFile(file)
                        Log.d("MyApp", "Image URI : " + uriFromPath)

                        images.add(it)
                        adapter!!.notifyChange(images)

                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }
                }
            }
        } else if (resultCode == Activity.RESULT_OK) {
            val imgUri = getRealPathFromUri(requireContext(), image_uri!!)
            images.add(imgUri)
            adapter!!.notifyChange(images)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_save_pictures -> {

                if (images == null || images.count() == 0) {
                    Toast.makeText(
                        requireContext(),
                        "Please add 1 image at least",
                        Toast.LENGTH_LONG
                    ).show()
                    return false
                }

                var pictureRequests = ArrayList<PictureRequest>()

                for (it in images) {
                    val pictureRequest = PictureRequest()
                    if (!it.isNullOrBlank()) {
                        pictureRequest.file = File(it)
                        pictureRequest.fileUri = it

                        pictureRequests.add(pictureRequest)
                    }
                }

                ProgressDialog.show(requireContext(), false)

                viewModel.addPictures(pictureRequests).observe(this) {
                    it.forEach() {
                        uploadedPictureIds.add(it.PictureId!!)
                    }



                    ProgressDialog.dismiss()

                    val bundle = Bundle().apply {
                        putIntegerArrayList(UPLOADED_PICTURES_IDS, uploadedPictureIds)
                        putString(COVER_IMAGE, images.firstOrNull())
                    }
                    navigateUp(NewProductStepOneFragment.REQUEST_CODE, bundle)
                }

            }
        }

        return super.onOptionsItemSelected(item);
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //called when user presses ALLOW or DENY from Permission Request Popup
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup was granted
                    openCamera()
                } else {
                    //permission from popup was denied
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = requireContext().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

    private fun getRealPathFromUri(context: Context, contentUri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor!!.moveToFirst()
            return cursor!!.getString(column_index)
        } finally {
            if (cursor != null) {
                cursor!!.close()
            }
        }
    }

    private fun permissionIfNeeded(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                )
                return true
            }
        }
        return false
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductPicturesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductPicturesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }

        private val OPEN_MEDIA_PICKER = 1  // Request code

        private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE =
            100 // Request code for read external storage

        public val UPLOADED_PICTURES_IDS = "UPLOADED_PICTURES_IDS"
        public val COVER_IMAGE = "COVER_IMAGE"
    }
}
