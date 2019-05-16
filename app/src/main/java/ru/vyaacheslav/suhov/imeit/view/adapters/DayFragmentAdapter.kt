package ru.vyaacheslav.suhov.imeit.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.vyaacheslav.suhov.imeit.R
import ru.vyaacheslav.suhov.imeit.repository.entity.Schedule
import ru.vyaacheslav.suhov.imeit.view.adapters.entity.TimeData

class DayFragmentAdapter(private val list: ArrayList<Schedule>) : RecyclerView.Adapter<DayFragmentAdapter.ViewHolder>() {

    private val listTime: List<TimeData> = listOf(TimeData("8","30","10:05"),
            TimeData("10","15","11:50"),TimeData("12","30","14:05"),
            TimeData("14","15","15:50"))

    override fun onCreateViewHolder(p: ViewGroup, t: Int) =
            ViewHolder(LayoutInflater.from(p.context).inflate(R.layout.item_schedule, p, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.lesson.text = list[position].lesson
        holder.teacher.text = list[position].teacher
        holder.type.text = list[position].type
        holder.building.text = list[position].building
        holder.lesson2.text = list[position].lesson2
        holder.teacher2.text = list[position].teacher2
        holder.type2.text = list[position].type2
        holder.building2.text = list[position].building2

        holder.time1.text = listTime[position].tex1
        holder.time2.text = listTime[position].tex2
        holder.time3.text = listTime[position].tex3
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val lesson: TextView = v.findViewById(R.id.s_name)
        val teacher: TextView = v.findViewById(R.id.s_subname)
        val type: TextView = v.findViewById(R.id.s_type)
        val building: TextView = v.findViewById(R.id.s_build)
        val lesson2: TextView = v.findViewById(R.id.s_name_d)
        val teacher2: TextView = v.findViewById(R.id.s_subname_d)
        val type2: TextView = v.findViewById(R.id.s_type_d)
        val building2: TextView = v.findViewById(R.id.s_build_d)

        val time1: TextView = v.findViewById(R.id.tex1)
        val time2: TextView = v.findViewById(R.id.tex2)
        val time3: TextView = v.findViewById(R.id.tex3)
    }
}