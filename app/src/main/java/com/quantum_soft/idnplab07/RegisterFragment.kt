package com.quantum_soft.idnplab07

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quantum_soft.idnplab07.databinding.FragmentRegisterBinding
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val author = binding.etAuthor.text.toString()
            val title = binding.etTitle.text.toString()
            val technique = binding.etTechnique.text.toString()
            val category = binding.etCategory.text.toString()
            val description = binding.etDescription.text.toString()
            val year = binding.etYear.text.toString()

            val paintingData = "$author|$title|$technique|$category|$description|$year"

            activity?.openFileOutput("painting.txt", Context.MODE_PRIVATE).use {
                it?.write(paintingData.toByteArray())
            }
        }
        binding.buttonGoToShow.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, ViewFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}