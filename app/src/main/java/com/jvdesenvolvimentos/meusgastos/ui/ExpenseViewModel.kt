package com.jvdesenvolvimentos.meusgastos.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jvdesenvolvimentos.meusgastos.data.Expense
import com.jvdesenvolvimentos.meusgastos.data.ExpenseDatabase
import com.jvdesenvolvimentos.meusgastos.network.CurrencyApi
import com.jvdesenvolvimentos.meusgastos.network.CurrencyResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface CurrencyUiState {
    object Loading : CurrencyUiState
    data class Success(val response: CurrencyResponse) : CurrencyUiState
    data class Error(val message: String) : CurrencyUiState
}

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val expenseDao = ExpenseDatabase.getDatabase(application).expenseDao()

    val expensesList: StateFlow<List<Expense>> = expenseDao.getAllExpenses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _currencyUiState = MutableStateFlow<CurrencyUiState>(CurrencyUiState.Loading)
    val currencyUiState: StateFlow<CurrencyUiState> = _currencyUiState.asStateFlow()

    init {
        fetchExchangeRates()
    }

    fun fetchExchangeRates() {
        viewModelScope.launch {
            _currencyUiState.value = CurrencyUiState.Loading
            try {
                val response = CurrencyApi.retrofitService.getExchangeRates()
                _currencyUiState.value = CurrencyUiState.Success(response)
            } catch (e: Exception) {
                _currencyUiState.value = CurrencyUiState.Error(
                    e.localizedMessage ?: "Erro ao carregar cotações."
                )
            }
        }
    }

    fun addExpense(title: String, amount: Double, category: String, date: String) {
        viewModelScope.launch {
            val expense = Expense(
                title = title,
                amount = amount,
                category = category,
                date = date
            )
            expenseDao.insertExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseDao.deleteExpense(expense)
        }
    }
}
