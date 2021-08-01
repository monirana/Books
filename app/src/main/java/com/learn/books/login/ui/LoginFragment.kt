package com.learn.books.login.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.learn.books.R
import com.learn.books.databinding.LoginFragmentBinding
import com.learn.books.login.domain.LoginValidationResult


/**
 * [LoginFragment] takes the user entered credentials and validate them,
 * on success shows the next screen
 */
public class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var navController: NavController

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        navController = Navigation.findNavController(view)

        binding.buttonLogin.setOnClickListener { v ->
            closeKeyboard()
            binding.passwordInputLayout.error = null
            binding.userNameInputLayout.error = null
            when (val loginValidationResult = loginViewModel.validateUser(
                username = binding.username.text.toString(),
                password = binding.password.text.toString()
            )) {
                is LoginValidationResult.EmailNotValid ->
                    binding.userNameInputLayout.error = loginValidationResult.message

                is LoginValidationResult.PasswordNotValid ->
                    binding.passwordInputLayout.error = loginValidationResult.message

                is LoginValidationResult.Success -> {
                    val bundle = bundleOf("userName" to binding.username.text.toString().trim())
                    navController.navigate(R.id.action_loginFragment_to_homeFragment, bundle)
                }
            }
        }

    }

    private fun closeKeyboard() {
        if (view != null) {
            val imm: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }
    }

}

