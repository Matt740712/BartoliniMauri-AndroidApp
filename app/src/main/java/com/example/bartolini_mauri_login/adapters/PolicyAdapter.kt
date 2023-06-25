package com.example.bartolini_mauri_login.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bartolini_mauri_login.R
import com.example.bartolini_mauri_login.models.policy.Policy
import java.util.*

class PolicyAdapter(private var policies: ArrayList<Policy>): RecyclerView.Adapter<PolicyAdapter.CustomViewHolder>() {
    class CustomViewHolder(val view: ViewGroup) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_policy_auto, parent, false) as ViewGroup
        return CustomViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val policy = policies[position]

        holder.view.findViewById<TextView>(R.id.numberPolicyTextView).text = "${holder.view.findViewById<TextView>(R.id.numberPolicyTextView).text} ${policy.number}"
        holder.view.findViewById<TextView>(R.id.companyNameTextView).text = policy.insurance
        holder.view.findViewById<TextView>(R.id.vehicleTextView).text = policy.subtitle
        holder.view.findViewById<TextView>(R.id.expirationDateText).text = policy.expiringDate
    }

    override fun getItemCount(): Int = policies.size
}