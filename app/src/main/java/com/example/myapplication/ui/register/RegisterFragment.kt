package com.example.myapplication.ui.register

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentRegisterBinding
import com.example.myapplication.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment(), RegisterView {
    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = RegisterPresenter()
        presenter.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentRegisterBinding>(inflater, R.layout.fragment_register, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register.setOnClickListener {
            val nickname = nickname_view.text.toString()
            val introduction = introduction_view.text.toString()
            val password = password_view.text.toString()
            presenter.register(nickname, introduction, password)
        }
    }

    override fun setLogin() {
        (activity as? MainActivity)?.setLogin()
        activity?.onBackPressed()
    }
}