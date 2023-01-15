package com.spbdemosss.myapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spbdemosss.myapplication.data.ShopListRepositoryImpl
import com.spbdemosss.myapplication.domain.DeleteShopItemUseCase
import com.spbdemosss.myapplication.domain.EditShopItemUseCase
import com.spbdemosss.myapplication.domain.GetShopListUseCase
import com.spbdemosss.myapplication.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}