package com.atom.android.chapter153_bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.atom.android.chapter153_bottomsheet.databinding.FragmentSecondBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val view: View = binding.designBottomSheet
        val bottomSheetBehavior = BottomSheetBehavior.from(view)


        bottomSheetBehavior.addBottomSheetCallback(
            object : BottomSheetBehavior.BottomSheetCallback(){
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_DRAGGING -> Log.i(
                            "BottomSheetCallback",
                            "BottomSheetBehavior.STATE_DRAGGING"
                        )
                        BottomSheetBehavior.STATE_SETTLING -> Log.i(
                            "BottomSheetCallback",
                            "BottomSheetBehavior.STATE_SETTLING"
                        )
                        BottomSheetBehavior.STATE_EXPANDED -> Log.i(
                            "BottomSheetCallback",
                            "BottomSheetBehavior.STATE_EXPANDED"
                        )
                        BottomSheetBehavior.STATE_COLLAPSED -> Log.i(
                            "BottomSheetCallback",
                            "BottomSheetBehavior.STATE_COLLAPSED"
                        )
                        BottomSheetBehavior.STATE_HIDDEN -> Log.i(
                            "BottomSheetCallback",
                            "BottomSheetBehavior.STATE_HIDDEN"
                        )
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

            }
        )


        binding.button.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}