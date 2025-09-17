package com.example.idlerealms.ui.world
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.idlerealms.databinding.FragmentWorldBinding

class WorldFragment : Fragment() {

    private var _binding: FragmentWorldBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val worldViewModel =
            ViewModelProvider(this).get(WorldViewModel::class.java)

        _binding = FragmentWorldBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textWorld
        worldViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}