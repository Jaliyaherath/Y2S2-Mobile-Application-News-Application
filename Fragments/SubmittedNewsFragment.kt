package com.example.labtest3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SubmittedNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubmittedNewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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


        // Inflate the layout for this fragment
        val rootView=inflater.inflate(R.layout.fragment_submitted_news,container,false)
        val txtnview:EditText=rootView.findViewById(R.id.txtnview)
        val btnclicksubmit:Button=rootView.findViewById(R.id.btnclicksubmit)



        btnclicksubmit.setOnClickListener{
            Toast.makeText(requireActivity(),"Submit Button clicked", Toast.LENGTH_LONG).show()
        }

        val viewModel=ViewModelProvider(requireActivity())[FragmentViewModel::class.java]

        viewModel.getNewsText().observe(viewLifecycleOwner) {
                newsText -> txtnview.setText(newsText)
        }


        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SubmittedNewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubmittedNewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}