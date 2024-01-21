package com.example.examen_moviles01

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class AutosFormEdit: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arrayAutos = InformationDDBB.autosList

    @SuppressLint("CutPasteId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autos_form_edit)


        val autoSelect = intent.getSerializableExtra("Auto") as Autos

        val id = findViewById<EditText>(R.id.editText_id_auto_Edit)
        val nombreAuto = findViewById<EditText>(R.id.editText_nombre_auto_Edit)
        val cantidad = findViewById<EditText>(R.id.editText_cantidad_auto_Edit)
        val precio = findViewById<EditText>(R.id.editText_precio_auto_Edit)
        val electrico = findViewById<EditText>(R.id.editText_electrico_auto_Edit)
        val tipo = findViewById<EditText>(R.id.editText_tipo_auto_Edit)

        id.setText(autoSelect.codigoConcesionario.toString())
        nombreAuto.setText(autoSelect.nombreAuto.toString())
        cantidad.setText(autoSelect.cantidad.toString())
        precio.setText(autoSelect.precio.toString())
        electrico.setText(autoSelect.electrico.toString())
        tipo.setText(autoSelect.tipo.toString())

        id.isEnabled = false

        val nombreDos = findViewById<EditText>(R.id.editText_nombre_auto_Edit)
        val idAuto = nombreDos.text.toString()
        val ind = buscarIndice(idAuto)

        val botonAceptarEditarF = findViewById<Button>(R.id.btn_aceptar_auto_form_Edit)
        botonAceptarEditarF.setOnClickListener {
            arrayAutos[ind].codigoConcesionario = findViewById<EditText>(R.id.editText_id_auto_Edit).text.toString().toInt()
            arrayAutos[ind].nombreAuto = findViewById<EditText>(R.id.editText_nombre_auto_Edit).text.toString()
            arrayAutos[ind].cantidad= findViewById<EditText>(R.id.editText_cantidad_auto_Edit).text.toString().toInt()
            arrayAutos[ind].precio= findViewById<EditText>(R.id.editText_precio_auto_Edit).text.toString().toDouble()
            arrayAutos[ind].electrico= findViewById<EditText>(R.id.editText_electrico_auto_Edit).text.toString().toBoolean()
            arrayAutos[ind].tipo = findViewById<EditText>(R.id.editText_tipo_auto_Edit).text.toString()

            finish()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun buscarIndice(nombreBuscado:String):Int{

        var idEncontrado: Int = -1

        for (autos in arrayAutos) {
            if (autos.nombreAuto == nombreBuscado) {
                idEncontrado = arrayAutos.indexOf(autos)
                break
            }
        }
        return idEncontrado
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase);
        startActivity(intent);
    }
}