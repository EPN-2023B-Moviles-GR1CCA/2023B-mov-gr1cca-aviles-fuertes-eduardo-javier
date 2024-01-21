package com.example.examen_moviles01

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class AutosForm : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arregloF = InformationDDBB.autosList

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autos_form)

        val concesionarioSelect = intent.getIntExtra("idConcesionario", -1)
        var idConcesionario = findViewById<EditText>(R.id.editText_id_auto)
        idConcesionario.setText(concesionarioSelect.toString())
        idConcesionario.isEnabled = false

        Log.i("TAG", "Form de autos del concesionario ${concesionarioSelect}")

        val btnAceptCreationAutos = findViewById<Button>(R.id.btn_acept_autos_form)
        btnAceptCreationAutos.setOnClickListener {
            var id = findViewById<EditText>(R.id.editText_id_auto).text.toString().toInt()
            var nombre = findViewById<EditText>(R.id.editText_nombre_auto).text.toString()
            var cantidad = findViewById<EditText>(R.id.editText_cantidad_autos).text.toString().toInt()
            var precio = findViewById<EditText>(R.id.editText_precio_auto).text.toString().toDouble()
            var electrico = findViewById<EditText>(R.id.editText_electrico_auto).text.toString().toBoolean()
            var tipo = findViewById<EditText>(R.id.editText_tipo_auto).text.toString()

            Log.i("TAG", "Form de autos del concesionario ${id}")
            arregloF.add(Autos(id,nombre,cantidad,precio,electrico,tipo))
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