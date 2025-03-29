package com.example.androiduicomponents.molecules.models

data class ButtonGroupListModel(
    val label: String? = null,
    val contentDescription: String? = null,
    val isChecked : Boolean = false,
    val items : List<String>
)