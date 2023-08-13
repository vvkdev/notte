package com.vvkdev.notte

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vvkdev.notte.databinding.FragmentListBinding
import com.vvkdev.notte.db.DbManager

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val dbManager: DbManager by lazy { DbManager(requireContext()) }
    private val adapter: NoteAdapter by lazy { NoteAdapter(mutableListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rv.layoutManager = LinearLayoutManager(requireContext())
            rv.adapter = adapter
            fabAdd.setOnClickListener { findNavController().navigate(R.id.editFragment) }
        }
    }

    override fun onResume() {
        super.onResume()
        val list = dbManager.readDb()
        adapter.updateAdapter(list)
        if (list.size > 0) {
            binding.tvNoElements.visibility = View.GONE
        } else {
            binding.tvNoElements.visibility = View.VISIBLE
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