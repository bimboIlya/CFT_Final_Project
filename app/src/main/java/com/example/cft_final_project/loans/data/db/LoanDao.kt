package com.example.cft_final_project.loans.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cft_final_project.loans.data.model.Loan

@Dao
interface LoanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(loanList: List<Loan>)

    @Query("SELECT * FROM loan ORDER BY id DESC")
    suspend fun getAllLoans(): List<Loan>

    @Query("SELECT * FROM loan")
    fun observeAllLoans(): LiveData<List<Loan>>

    @Query("SELECT * FROM loan WHERE id = :id")
    suspend fun getLoanById(id: Int): Loan

    @Query("DELETE FROM loan")
    suspend fun dropTable()
}