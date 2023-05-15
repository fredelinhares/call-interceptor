package com.example.callinterceptor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentC : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phoneNumber = arguments?.getString("phoneNumber")
        val textView: TextView? = view.findViewById(R.id.textView_c)
        textView?.text = phoneNumber ?: "Número não identifica ou nenhuma chamada feita ainda!"
    }
}