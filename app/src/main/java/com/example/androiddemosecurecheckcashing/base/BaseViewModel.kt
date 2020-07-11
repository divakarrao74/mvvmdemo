package com.example.androiddemosecurecheckcashing.base

import androidx.lifecycle.AndroidViewModel
import com.example.androiddemosecurecheckcashing.application.DemoApplication
import java.lang.ref.WeakReference

abstract class BaseViewModel() : AndroidViewModel(DemoApplication()) {
}