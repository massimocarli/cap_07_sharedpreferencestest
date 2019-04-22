package uk.co.massimocarli.sharedpreferencestest

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  companion object {
    const val STRING_KEY = "stringValue"
    const val INT_KEY = "intValue"
    const val BOOLEAN_KEY = "booleanValue"
  }

  lateinit var sharedPreferences: SharedPreferences

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    sharedPreferences = getPreferences(Context.MODE_PRIVATE)
    // We read the value
    with(sharedPreferences) {
      getString(STRING_KEY, null)?.let {
        stringValue.setText(it)
      }
      getInt(INT_KEY, 0).let {
        numberValue.setText("$it")
      }
      getBoolean(BOOLEAN_KEY, false).let {
        booleanValue.isChecked = it
      }
    }
  }

  fun saveData(view: View) {
    sharedPreferences.edit()
      .putString(STRING_KEY, stringValue.text.toString())
      .putInt(INT_KEY, Integer.parseInt(numberValue.text.toString()))
      .putBoolean(BOOLEAN_KEY, booleanValue.isChecked)
      .apply()
  }

  fun saveDataK(view: View) {
    sharedPreferences.edit(commit = false) {
      putString(STRING_KEY, stringValue.text.toString())
      putInt(INT_KEY, Integer.parseInt(numberValue.text.toString()))
      putBoolean(BOOLEAN_KEY, booleanValue.isChecked)
    }
  }
}