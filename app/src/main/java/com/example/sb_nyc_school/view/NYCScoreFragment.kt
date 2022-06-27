package com.example.sb_nyc_school.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sb_nyc_school.R
import com.example.sb_nyc_school.databinding.FragmentNycscoreBinding
import com.example.sb_nyc_school.model.NYCScore
import com.example.sb_nyc_school.utils.UIState

class NYCScoreFragment: ViewModelFragment() {

    private var _binding: FragmentNycscoreBinding? = null
    private val binding: FragmentNycscoreBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNycscoreBinding.inflate(layoutInflater)

        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.scoreLiveData.observe(viewLifecycleOwner) { state ->
            val school = viewModel.currentSchool
            when(state) {
                is UIState.Success<*> -> {
                    val score: NYCScore? = (state.response as List<NYCScore>).firstOrNull()
                    if (score == null) {
                        binding.apply {
                            pbScoreLoading.visibility = View.GONE
                            tvScoreLoadingText.visibility = View.GONE
                        }
                    } else {
                        binding.apply {
                            pbScoreLoading.visibility = View.GONE
                            tvScoreLoadingText.visibility = View.GONE
                            tvScoreTakers.text = resources.getString(R.string.score_takers, score.numOfSatTestTakers)
                            tvScoreMath.text = resources.getString(R.string.score_math, score.satMathAvgScore)
                            tvScoreReading.text = resources.getString(R.string.score_reading, score.satCriticalReadingAvgScore)
                            tvScoreWriting.text = resources.getString(R.string.score_writing, score.satWritingAvgScore)
                            llScores.visibility = View.VISIBLE
                        }
                    }
                    binding.apply {
                        tvScoreSchoolName.text = school?.schoolName
                        tvScoreAddress.text = resources.getString(R.string.score_address, school?.primaryAddressLine1)
                        tvScoreEmail.text = resources.getString(R.string.score_email, school?.schoolEmail)
                        tvScoreStudents.text = resources.getString(R.string.score_students, school?.totalStudents)
                        tvScoreOverview.text = resources.getString(R.string.score_overview,school?.overviewParagraph)
                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        pbScoreLoading.visibility = View.GONE
                        tvScoreLoadingText.text = state.exception.message
                    }
                }
                is UIState.Loading -> {
                    viewModel.fetchNYCScore(viewModel.currentSchool?.dbn ?: "")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}