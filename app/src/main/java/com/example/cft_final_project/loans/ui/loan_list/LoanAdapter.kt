package com.example.cft_final_project.loans.ui.loan_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cft_final_project.R
import com.example.cft_final_project.common.util.extensions.getString
import com.example.cft_final_project.databinding.ItemLoanBinding
import com.example.cft_final_project.loans.data.model.LoanUi

class LoanAdapter(
    private val vm: LoanListViewModel
) : ListAdapter<LoanUi, LoanAdapter.LoanViewHolder>(LoanDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLoanBinding.inflate(inflater, parent, false)

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
                root.setOnClickListener {
                    vm.navigateToLoanDetails(loan)
                }
                val statusColor = ContextCompat.getColor(root.context, loan.status.colorId)

                loanStatus.setText(loan.status.textId)
                loanStatus.setTextColor(statusColor)

                loanId.text = getString(R.string.loan_id, loan.id)
                loanId.setTextColor(statusColor)

                debtorName.text =
                    getString(R.string.debtor_name, loan.lastName, loan.firstName)
                debtorPhoneNumber.text =
                    getString(R.string.debtor_phone_number_short, loan.phoneNumber)
                loanAmount.text = getString(R.string.loan_amount, loan.amount)
                loanPercent.text = getString(R.string.loan_percent, loan.percent)
                loanPeriod.text = getString(R.string.loan_period, loan.period)
                loanDate.text = loan.date
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