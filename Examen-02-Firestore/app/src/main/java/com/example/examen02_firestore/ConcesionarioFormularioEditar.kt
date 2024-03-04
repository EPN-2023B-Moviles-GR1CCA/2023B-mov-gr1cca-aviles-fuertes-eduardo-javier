package com.example.examen02_firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConcesionarioFormularioEditar : AppCompatActivity() {
    private lateinit var botonGuardar: Button
    private lateinit var botonCancelar: Button
    private lateinit var concesionario: Concesionarios

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concesionario_formulario_editar)

        concesionario = intent.getParcelableExtra(EXTRA_CONCESIONARIO)!!  // para obtener un objeto Parcelable de un Intent. El operador !!  para forzar el objeto obtenido a ser no nulo,

        // Inicializar los campos de texto con los datos deL concesionario
        val nombre = findViewById<EditText>(R.id.txt_nombre_concesionario)
        val direccion = findViewById<EditText>(R.id.txt_direccion)
        val cantidadEmpleados = findViewById<EditText>(R.id.txt_empleados)
        val estaAbierto = findViewById<CheckBox>(R.id.chk_abierto)
        val ingresosSemanales = findViewById<EditText>(R.id.txt_ingresos)

        nombre.setText(concesionario.nombre)
        direccion.setText(concesionario.direccion)
        cantidadEmpleados.setText(concesionario.numeroEmpleados.toString())
        estaAbierto.isChecked = concesionario.estaAbierto ?:false
        ingresosSemanales.setText(concesionario.ingresosSemanales?.toString())

        botonGuardar = findViewById(R.id.btn_guardar_concesionario)
        botonCancelar = findViewById(R.id.btn_cancelar_concesionario)

        botonGuardar.setOnClickListener {
            guardarConcesionario()
        }

        botonCancelar.setOnClickListener {
            finish()
        }
    }

    private fun guardarConcesionario() {
        // Actualizar los valores del concesionario con los datos ingresados en los campos de texto
        concesionario.nombre = obtenerTexto(R.id.txt_nombre_concesionario)
        concesionario.direccion = obtenerTexto(R.id.txt_direccion)
        concesionario.numeroEmpleados = obtenerTextoLong(R.id.txt_empleados)
        concesionario.estaAbierto = obtenerValorBooleano(R.id.chk_abierto)
        concesionario.ingresosSemanales = obtenerTextoDouble(R.id.txt_ingresos)

        val db= Firebase.firestore
        val concesionarioId = concesionario.id

        if (concesionarioId != null) {
            // Crear un mapa de datos solo con los campos que deseas actualizar
            val actualizaciones = hashMapOf<String, Any?>()
            actualizaciones["nombre"] = concesionario.nombre
            actualizaciones["direccion"] = concesionario.direccion
            actualizaciones["numeroEmpelados"] = concesionario.numeroEmpleados
            actualizaciones["estaAbierto"] = concesionario.estaAbierto
            actualizaciones["ingresosSemanales"] = concesionario.ingresosSemanales

            db.collection("concesionarios")
                .document(concesionarioId)
                .update(actualizaciones)
                .addOnSuccessListener {
                    // La actualización fue exitosa
                    finish()
                }
                .addOnFailureListener { e ->
                    // Ocurrió un error durante la actualización
                    // Manejar el error según sea necesario
                }

        } else {
            // Manejar el caso en el que el ID del documento sea nulo
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
        const val EXTRA_CONCESIONARIO = "concesionario" // Mismo nombre de constante que en ListvContinente
    }
}