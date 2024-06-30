package com.quantum_soft.idnplab07


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quantum_soft.idnplab07.databinding.FragmentViewBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class ViewFragment : Fragment() {

    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.openFileInput("painting.txt")?.use { stream ->
            val reader = BufferedReader(InputStreamReader(stream))
            val paintingData = reader.readLine().split("|")
            if (paintingData.size == 6) {
                binding.tvAuthor.text = "Autor: " + paintingData[0]
                binding.tvTitle.text = "Título: " + paintingData[1]
                binding.tvTechnique.text = "Técnica: " + paintingData[2]
                binding.tvCategory.text = "Categoría: " + paintingData[3]
                binding.tvDescription.text = "Descripción: " + paintingData[4]
                binding.tvYear.text = "Año: " + paintingData[5]
            }
        }
        binding.buttonGoToRegister.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, RegisterFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}