package com.udacity.shoestore.models.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: ShoeDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ShoeDetailsFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        val shoeViewModel: ShoeViewModel by activityViewModels()
        binding.shoeViewModel = shoeViewModel

        binding.saveButton.setOnClickListener {
            if (!checkTextFieldsForErrors()) {
                shoeViewModel.addShoe()
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(
                ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment()
            )
        }
        return binding.root
    }

    private fun checkTextFieldsForErrors(): Boolean {
        var error = false
        if (checkEmptyFieldsCheck(binding.nameLayout, "Name")) {
            error = true
        }
        if (checkEmptyFieldsCheck(binding.brandLayout, "Brand")) {
            error = true
        }
        if (checkEmptyFieldsCheck(binding.descriptionLayout, "Description")) {
            error = true
        }
        if (checkEmptyFieldsCheck(binding.sizeLayout, "Size")) {
            error = true
        }
        return error
    }

    private fun checkEmptyFieldsCheck(field: TextInputLayout, label: String): Boolean {
        if (field.editText?.text.toString().isBlank()) {
            field.error = "$label is required"
            return true
        } else {
            field.error = null
            return false
        }
    }


}