package com.example.cft_final_project.loans.ui.loan_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cft_final_project.databinding.ItemLoanBinding
import com.example.cft_final_project.loans.data.model.LoanUi

class LoanAdapter(
    private val vm: LoanListViewModel
) : ListAdapter<LoanUi, LoanAdapter.LoanViewHolder>(LoanDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLoanBinding.inflate(inflater)

        return LoanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        holder.bind(currentList[position], vm)
    }


    class LoanViewHolder(
        private val binding: ItemLoanBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loan: LoanUi, vm: LoanListViewModel) {
            binding.apply {
                this.viewModel = vm
                this.loan = loan
                executePendingBindings()
            }
        }
    }
}

private class LoanDiffUtil : DiffUtil.ItemCallback<LoanUi>() {
    override fun areItemsTheSame(oldItem: LoanUi, newItem: LoanUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LoanUi, newItem: LoanUi): Boolean {
        return oldItem == newItem
    }
}