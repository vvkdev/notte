package com.vvkdev.notte

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vvkdev.notte.databinding.FragmentEditBinding
import com.vvkdev.notte.db.DbManager

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val dbManager: DbManager by lazy { DbManager(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabSave.setOnClickListener {
            with(binding) {
                val title = edTitle.text.toString()
                val content = edContent.text.toString()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    dbManager.insertToDb(title, content)
                    findNavController().popBackStack()
                } else {
                    Toast
                        .makeText(requireContext(), R.string.empty_note, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        dbManager.closeDb()
        super.onDestroy()
    }
}