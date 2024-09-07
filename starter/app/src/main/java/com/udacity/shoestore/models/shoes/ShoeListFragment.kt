package com.udacity.shoestore.models.shoes

import android.os.Bundle

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {
    private lateinit var binding: ShoeListFragmentBinding
    private lateinit var shoeItemBinding: ShoeListItemBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = ShoeListFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        val shoeViewModel: ShoeViewModel by activityViewModels()

        val view = binding.root
        binding.shoeViewModel = shoeViewModel

        shoeViewModel.addShoeFlag.observe(viewLifecycleOwner, Observer { isAdded ->
            if (isAdded) {
                findNavController().navigate(ShoeListFragmentDirections.actionShoesFragmentToShoeDetailFragment())
                shoeViewModel.addShoeComplete()
            }
        })

        shoeViewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach {
                createTextView(it)
            }
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        return true
    }

    private fun createTextView(shoe: Shoe) {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        shoeItemBinding = ShoeListItemBinding.inflate(inflater, binding.shoeList, false)
        shoeItemBinding.shoe = shoe
        binding.shoeList.addView(shoeItemBinding.root)
    }

}
