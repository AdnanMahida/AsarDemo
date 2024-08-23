package com.ad.asardemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ad.asardemo.databinding.FragmentEmptyBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EmptyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_SCREEN = "screenName"

class EmptyFragment : Fragment() {
    private var _binding: FragmentEmptyBinding? = null
    private val binding get() = _binding!!

    private var screenName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            screenName = it.getString(ARG_SCREEN)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmptyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = screenName.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance(screenName: String) =
            EmptyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SCREEN, screenName)
                }
            }
    }
}