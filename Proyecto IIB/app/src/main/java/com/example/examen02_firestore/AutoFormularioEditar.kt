package com.example.examen02_firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AutoFormularioEditar : AppCompatActivity() {
    private lateinit var botonGuardar: Button
    private lateinit var botonCancelar: Button
    private lateinit var auto: Autos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_formulario_editar)

        auto = intent.getParcelableExtra(EXTRA_AUTO)!!

        // Inicializar los campos de texto con los datos
        val nombre = findViewById<EditText>(R.id.txt_nombre_auto)
        val cantidad = findViewById<EditText>(R.id.txt_cantidad)
        val precioUnitario = findViewById<EditText>(R.id.txt_precio)
        val esElectrico = findViewById<CheckBox>(R.id.chk_electrico)
        val tipoAuto= findViewById<EditText>(R.id.txt_tipo)

        nombre.setText(auto.nombre)
        esElectrico.isChecked = auto.esElectrico ?:false
        cantidad.setText(auto.cantidad.toString())
        precioUnitario.setText(auto.precioUnitario?.toString())
        tipoAuto.setText(auto.tipoAuto)

        botonGuardar = findViewById(R.id.btn_guardar_auto)
        botonCancelar = findViewById(R.id.btn_cancelar_auto )

        botonGuardar.setOnClickListener {
            guardarAuto()
        }

        botonCancelar.setOnClickListener {
            finish()
        }
    }

    private fun guardarAuto() {
        // Actualizar los valores del continente con los datos ingresados en los campos de texto
        auto.nombre = obtenerTexto(R.id.txt_nombre_auto)
        auto.cantidad = obtenerTextoLong(R.id.txt_cantidad)
        auto.precioUnitario = obtenerTextoDouble(R.id.txt_precio)
        auto.esElectrico = obtenerValorBooleano(R.id.chk_electrico)
        auto.tipoAuto = obtenerTexto(R.id.txt_tipo)

        val db= Firebase.firestore
        val autoId=auto.id

        if (autoId != null) {
            val actualizaciones = hashMapOf<String, Any?>()
            actualizaciones["nombre"] = auto.nombre
            actualizaciones["cantidad"] = auto.cantidad
            actualizaciones["precioUnitario"] = auto.precioUnitario
            actualizaciones["esElectrico"] = auto.esElectrico
            actualizaciones["tipoAuto"] = auto.tipoAuto

            db.collection("Auto")
                .document(autoId)
                .update(actualizaciones)
                .addOnSuccessListener { finish() }
                .addOnFailureListener { }
        }else{

        }
    }

    private fun obtenerTexto(id: Int): String {
        val editText = findViewById<EditText>(id)
        return editText.text.toString()
    }
    private fun obtenerTextoLong(id: Int): Long {
        val editText = findViewById<EditText>(id)
        val texto = editText.text.toString()
        return if (texto.isNotEmpty()) texto.toLong() else 0L
    }
    private fun obtenerTextoDouble(id: Int): Double {
        val editText = findViewById<EditText>(id)
        val texto = editText.text.toString()
        return if (texto.isNotEmpty()) texto.toDouble() else 0.0
    }
    private fun obtenerValorBooleano(id: Int): Boolean {
        val checkBox = findViewById<CheckBox>(id)
        return checkBox.isChecked
    }

    companion object {
        const val EXTRA_AUTO = "auto" // Mismo nombre de constante que en ListvPais
    }
}