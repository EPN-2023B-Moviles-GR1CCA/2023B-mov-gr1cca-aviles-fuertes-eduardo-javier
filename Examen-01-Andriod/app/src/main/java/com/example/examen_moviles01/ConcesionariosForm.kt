package com.example.examen_moviles01

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class ConcesionariosForm: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arrayAux = InformationDDBB.concesionariosList

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concesionarios_form)

        val btnAceptarCreacion = findViewById<Button>(R.id.btn_aceptar_concesionario_form)
        btnAceptarCreacion.setOnClickListener {
            var id = findViewById<EditText>(R.id.editText_id_concesionario).text.toString().toInt()
            var nombre = findViewById<EditText>(R.id.editText_nombre_concesionario).text.toString()
            var direccion = findViewById<EditText>(R.id.editText_direccion_concesionario).text.toString()
            var cantidadVendedores = findViewById<EditText>(R.id.editText_cantidad_vendedores_concesionario).text.toString().toInt()
            var vendeUsados = findViewById<EditText>(R.id.editText_vende_usados_concesionario).text.toString().toBoolean()
            var totalPrecioLote = findViewById<EditText>(R.id.editText_total_precio_concesionario).text.toString().toDouble()

            arrayAux.add(Concesionarios(id,nombre,direccion,cantidadVendedores,vendeUsados,totalPrecioLote))

            finish()
        }

    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase);
        startActivity(intent);
    }
}