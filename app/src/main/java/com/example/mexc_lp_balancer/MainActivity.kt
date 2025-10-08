package com.example.mexc_lp_balancer

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// Если у тебя есть собственные классы ApiClient, Prefs, BalancerManager в all.ktl,
// скопируй их в этот пакет (com.example.mexc_lp_balancer) и удалите / адаптируйте дубли.

class MainActivity : AppCompatActivity() {

    private lateinit var modeSwitch: Switch
    private lateinit var modeLabel: TextView
    private lateinit var apiKeyInput: EditText
    private lateinit var apiSecretInput: EditText
    private lateinit var saveButton: Button
    private lateinit var statusLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modeSwitch = findViewById(R.id.switchMode)
        modeLabel = findViewById(R.id.textMode)
        apiKeyInput = findViewById(R.id.inputApiKey)
        apiSecretInput = findViewById(R.id.inputApiSecret)
        saveButton = findViewById(R.id.btnSaveKeys)
        statusLabel = findViewById(R.id.statusLabel)

        // Заглушка: тут должна быть реализация Prefs из твоего кода.
        // Пока используем простые сохранения в памяти — заменишь на Prefs при вставке своего кода.
        val prefs = object {
            var apiKey = ""
            var apiSecret = ""
            var testMode = true
        }

        // Подставляем ранее "сохранённые" (в шаблоне это пусто)
        apiKeyInput.setText(prefs.apiKey)
        apiSecretInput.setText(prefs.apiSecret)
        modeSwitch.isChecked = prefs.testMode
        updateModeLabel(prefs.testMode)

        saveButton.setOnClickListener {
            prefs.apiKey = apiKeyInput.text.toString().trim()
            prefs.apiSecret = apiSecretInput.text.toString().trim()
            statusLabel.text = "Ключи сохранены (в памяти). Замените Prefs на реальную реализацию."
        }

        modeSwitch.setOnCheckedChangeListener { _, isChecked ->
            prefs.testMode = isChecked
            updateModeLabel(isChecked)
        }
    }

    private fun updateModeLabel(isTestMode: Boolean) {
        modeLabel.text = if (isTestMode) "Тестовый режим" else "Боевой режим"
    }
}
