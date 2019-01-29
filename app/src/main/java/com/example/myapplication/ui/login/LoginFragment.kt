package com.example.myapplication.ui.login

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment(), LoginView {
    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = LoginPresenter()
        presenter.bindView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =
            DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener {
            val nickname = nickname_view.text.toString()
            val password = password_view.text.toString()
            presenter.login(nickname, password)
        }
    }

    override fun setLogin() {
        (activity as? MainActivity)?.setLogin()
        activity?.onBackPressed()
    }
}