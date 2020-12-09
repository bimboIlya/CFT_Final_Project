package com.example.cft_final_project.loans.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cft_final_project.loans.data.model.Loan

@Database(
    entities = [Loan::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun loanDao(): LoanDao
}