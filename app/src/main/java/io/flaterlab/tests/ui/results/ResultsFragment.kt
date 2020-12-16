package io.flaterlab.tests.ui.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import io.flaterlab.tests.R
import io.flaterlab.tests.ui.testing.ScoresAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*

class ResultsFragment : Fragment() {

    private lateinit var resultsViewModel: ResultsViewModel
    private lateinit var rv_score:  RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultsViewModel =
            ViewModelProvider(this).get(ResultsViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        rv_score = view.findViewById(R.id.rv_scores)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp = ResultsPreference(requireContext())
        if (sp.getUser() != null) {
            if (sp.getList(sp.getUser()!!) != null) {
                rv_score.apply {
                    adapter = ScoresAdapter(sp.getList(sp.getUser()!!)!!)
                    setHasFixedSize(true)
                }

            }
        }

    }
}