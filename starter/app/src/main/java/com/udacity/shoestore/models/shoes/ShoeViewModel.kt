package com.udacity.shoestore.models.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class ShoeViewModel() : ViewModel() {
    private val _userLogStatus = MutableLiveData<Boolean>()
    val userLogStatus: LiveData<Boolean>
        get() = _userLogStatus

    private val _addShoeFlag = MutableLiveData<Boolean>()
    val addShoeFlag: LiveData<Boolean>
        get() = _addShoeFlag
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList
    val shoeSize = MutableLiveData<String>()
    val newShoe = MutableLiveData(Shoe("", 0.0, "", ""))


    init {
        _shoeList.value = mutableListOf(
            Shoe(
                "Nike Air Force 1",
                10.0,
                "Nike",
                "A classic and iconic sneaker known for its simple yet stylish design, featuring a leather upper, perforated toe box for breathability, and a cushioned sole for comfort."
            ),
            Shoe("Nike Air Max 90", 11.0, "Nike", "A cool shoe"),
            Shoe("Nike Air Max 97", 12.0, "Nike", "A cool shoe"),
            Shoe(
                "Adidas Ultraboost 22",
                9.5,
                "Adidas",
                "High-performance running shoes with a Boost midsole for superior cushioning, a Primeknit upper for a snug fit, and a durable rubber outsole for traction."
            ),
            Shoe("Puma RS-X", 10.5, "Puma", "A cool shoe"),
            Shoe(
                "Timberland PRO 6\" Pit Boss",
                11.0,
                "Timberland",
                "Rugged work boots designed for durability and comfort, featuring full-grain leather, an anti-fatigue midsole, and a slip-resistant outsole."
            ),
            Shoe("New Balance 574", 12.0, "New Balance", "A cool shoe"),
            Shoe("Vans Old Skool", 10.0, "Vans", "A cool shoe"),
            Shoe("Converse Chuck Taylor All Star", 11.0, "Converse", "A cool shoe"),
            Shoe("Reebok Classic Leather", 12.0, "Reebok", "A cool shoe"),
            Shoe("Asics Gel-Nimbus", 10.0, "Asics", "A cool shoe"),
            Shoe("Mizuno Wave Rider", 11.0, "Mizuno", "A cool shoe"),
            Shoe("Nike Air Force 1", 10.0, "Nike", "A cool shoe"),
        )
    }

    fun addShoeNavigate() {
        _addShoeFlag.value = true
    }

    fun addShoeComplete() {
        _addShoeFlag.value = false
    }

    fun addShoe() {
        newShoe.value?.size = shoeSize.value?.toDouble() ?: 0.0
        _shoeList.value?.add(newShoe.value!!)
    }

}