package io.flaterlab.tests.ui.testing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.flaterlab.tests.R
import io.flaterlab.tests.data.model.UserDetail
import io.flaterlab.tests.ui.results.UserScore
import kotlinx.android.synthetic.main.items_scores.view.*

class ScoresAdapter(private var listOfScores: List<UserScore>)
    : RecyclerView.Adapter<ScoresAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items_scores, parent, false)
        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return listOfScores.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.userEmail.text = listOfScores[position].userEmail
        holder.itemView.score.text = listOfScores[position].userScore
    }



}