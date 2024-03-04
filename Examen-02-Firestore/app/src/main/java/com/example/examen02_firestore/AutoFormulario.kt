package com.example.examen02_firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class AutoFormulario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_formulario)

        val concesionarioId = intent.getStringExtra("concesionarioId") // Cambiamos el tipo de dato a String

        val botonGuardar = findViewById<Button>(R.id.btn_guardar_auto)
        val botonCancelar = findViewById<Button>(R.id.btn_cancelar_auto)

        botonGuardar.setOnClickListener {
            if (concesionarioId != null) {
                guardarAuto(concesionarioId)
            }
        }

        botonCancelar.setOnClickListener {
            finish()
        }
    }

    private fun guardarAuto(idF: String) {
        val nombre = obtenerTexto(R.id.txt_nombre_auto)
        val cantidad = obtenerTextoLong(R.id.txt_cantidad)
        val precioUnitario=obtenerTextoDouble(R.id.txt_precio)
        val esElectrico = obtenerValorBooleano(R.id.chk_electrico)
        val tipoAuto = obtenerTexto(R.id.txt_tipo )

        val db= Firebase.firestore
        val id= Date().time
        val conts = db.collection("autos")

        val data1 = hashMapOf(
            "nombre" to nombre,
            "cantidad" to cantidad,
            "precioUnitario" to precioUnitario,
            "esElectrico" to esElectrico,
            "tipoAuto" to tipoAuto,
            "concesionarioId" to idF
        )

        conts.document(id.toString()).set(data1)
            .addOnSuccessListener {

                finish()
            }
            .addOnFailureListener {  }
        conts.document(id.toString()).update("id",id.toString())

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

}