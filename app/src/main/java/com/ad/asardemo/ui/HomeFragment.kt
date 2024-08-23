package com.ad.asardemo.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ad.asardemo.R
import com.ad.asardemo.databinding.DialogYesNoBinding
import com.ad.asardemo.databinding.FragmentHomeBinding
import com.ad.asardemo.model.Crypto
import com.ad.asardemo.model.Question
import com.ad.asardemo.model.Sports
import com.ad.asardemo.model.Status
import com.ad.asardemo.ui.adapter.ImageSliderAdapter
import com.ad.asardemo.ui.adapter.QuestionAdapter
import com.ad.asardemo.ui.adapter.StatusAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val handler = Handler(Looper.getMainLooper())
    private var imageListSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTopItems()
        setUpImageSlider()
        setUpYesNoItems()
    }


    private fun setUpTopItems() {
        val adapter = StatusAdapter()
        binding.recycleTop.adapter = adapter
        binding.recycleTop.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        adapter.submitData(
            arrayListOf(
                Status(
                    1,
                    "Bitcoin",
                    Crypto(22.75, 44.86),
                    R.drawable.ic_bitcoin
                ),
                Status(
                    2,
                    "IPL",
                    Sports("2024"),
                    R.drawable.ic_cricket
                ),
                Status(
                    3,
                    "Ethereum",
                    Crypto(22.75, 44.86),
                    R.drawable.ic_etheriam
                ),
                Status(
                    4,
                    "Men's world cup",
                    Sports("Cricket"),
                    R.drawable.ic_cricket
                ),
                Status(
                    5,
                    "Champion league",
                    Sports("Football"),
                    R.drawable.ic_footbal
                )
            )
        )
    }

    private fun setUpImageSlider() {
        val imageList = listOf(
            R.drawable.ic_slider_1,
            R.drawable.ic_slider_2,
            R.drawable.ic_slider_3,
        )

        imageListSize = imageList.size

        val adapter = ImageSliderAdapter(imageList)
        binding.vpSlider.adapter = adapter

    }

    private val sliderRunnable = object : Runnable {
        override fun run() {
            binding.vpSlider.currentItem = (binding.vpSlider.currentItem + 1) % imageListSize
            handler.postDelayed(this, 2_000) // 2 seconds delay
        }
    }

    private fun setUpYesNoItems() {
        val adapter = QuestionAdapter()
        binding.recycleYesNo.adapter = adapter
        binding.recycleYesNo.layoutManager = LinearLayoutManager(requireContext())
        adapter.onYesNoClick { type ->
            when (type) {
                QuestionAdapter.Type.YES -> {
                    showYesNoDialog()
                }

                QuestionAdapter.Type.NO -> {
                    showYesNoDialog()
                }
            }

        }

        adapter.onDetailsClick {
            startActivity(Intent(requireContext(), QuestionActivity::class.java))
        }
        adapter.submitData(
            arrayListOf(
                Question(
                    1, "Kolkata to win the match vs Mumbai?",
                    "H2H last 5 T20 : Kolkata 4, Mumbai 1, DRAW 0", R.drawable.ic_ipl
                ),
                Question(
                    2, "Kolkata to win the match vs Mumbai?",
                    "H2H last 5 T20 : Kolkata 4, Mumbai 1, DRAW 0", R.drawable.ic_ipl
                ), Question(
                    3, "Kolkata to win the match vs Mumbai?",
                    "H2H last 5 T20 : Kolkata 4, Mumbai 1, DRAW 0", R.drawable.ic_ipl
                ), Question(
                    4, "Kolkata to win the match vs Mumbai?",
                    "H2H last 5 T20 : Kolkata 4, Mumbai 1, DRAW 0", R.drawable.ic_ipl
                )
            )
        )
    }

    private fun showYesNoDialog() {
        val bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.CustomBottomSheetDialogTheme)
        val bottomSheetBinding = DialogYesNoBinding.inflate(layoutInflater)

        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.apply {
            tvPlus.setOnClickListener {
                seekbar.progress += 1
            }
            tvMinus.setOnClickListener {
                seekbar.progress -= 1
            }
        }
        bottomSheetDialog.show()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(sliderRunnable, 2_000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment().apply { arguments = Bundle().apply {} }
    }
}