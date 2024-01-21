package com.example.examen_moviles01

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi

class ConcesionariosFormEdit : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arrayAux = InformationDDBB.concesionariosList


    @SuppressLint("CutPasteId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_concesionarios_form_edit)


        val concesionariosSelect = intent.getSerializableExtra("concesionario") as Concesionarios

        val id = findViewById<EditText>(R.id.editText_id_concesionario_edit)
        val nombre = findViewById<EditText>(R.id.editText_nombre_concesionario_edit)
        val direccion = findViewById<EditText>(R.id.editText_direccion_concesionario_edit)
        val cantidadVendedores = findViewById<EditText>(R.id.editText_cantidad_vendeores_concesionario_edit)
        val vendeUsados = findViewById<EditText>(R.id.editText_vende_usados_concesionario_edit)
        val totalPrecioLote = findViewById<EditText>(R.id.editText_total_precio_concesionario_edit)



        id.setText(concesionariosSelect.id.toString().toInt())
        nombre.setText(concesionariosSelect.nombre.toString())
        direccion.setText(concesionariosSelect.direccion.toString())
        cantidadVendedores.setText(concesionariosSelect.cantidadVendedores.toString())
        vendeUsados.setText(concesionariosSelect.vendeUsados.toString())
        totalPrecioLote.setText(concesionariosSelect.totalPrecioLote.toString())



        val idDos = findViewById<EditText>(R.id.editText_id_concesionario_edit)
        val idConcesionario = idDos.text.toString().toInt()
        val ind = buscarIndice(idConcesionario)

        id.isEnabled = false

        val btnAceptarEditar = findViewById<Button>(R.id.btn_aceptar_concesionario_form_edit)
        btnAceptarEditar.setOnClickListener {
            arrayAux[ind].id = findViewById<EditText>(R.id.editText_id_concesionario_edit).text.toString().toInt()
            arrayAux[ind].nombre = findViewById<EditText>(R.id.editText_nombre_concesionario_edit).text.toString()
            arrayAux[ind].direccion = findViewById<EditText>(R.id.editText_direccion_concesionario_edit).text.toString()
            arrayAux[ind].cantidadVendedores = findViewById<EditText>(R.id.editText_cantidad_vendeores_concesionario_edit).text.toString().toInt()
            arrayAux[ind].vendeUsados = findViewById<EditText>(R.id.editText_vende_usados_concesionario_edit).text.toString().toBoolean()
            arrayAux[ind].totalPrecioLote = findViewById<EditText>(R.id.editText_total_precio_concesionario_edit).text.toString().toDouble()
            //irActividad(MainActivity::class.java)
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun buscarIndice(idBuscado:Int):Int{

        var idEncontrado: Int = -1

        for (concesionario in arrayAux) {
            if (concesionario.id == idBuscado) {
                idEncontrado = arrayAux.indexOf(concesionario)
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