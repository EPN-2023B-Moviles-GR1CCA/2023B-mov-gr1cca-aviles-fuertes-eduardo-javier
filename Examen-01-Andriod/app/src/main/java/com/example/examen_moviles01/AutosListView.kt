package com.example.examen_moviles01

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class AutosListView : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    var arrayAux = InformationDDBB.autosList
    var idItemSeleccionado = 0

    var adaptador: ArrayAdapter<Autos>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autos_list_view)

        val concesionarioSelect = intent.getSerializableExtra("Concesionario") as Concesionarios
        arrayAux = InformationDDBB.autosList.filter { it.codigoConcesionario == concesionarioSelect.id }.toMutableList()

        val namePadre = findViewById<TextView>(R.id.tv_nombre_padre)
        namePadre.setText(concesionarioSelect.nombre)

        val listView = findViewById<ListView>(R.id.listView_autos)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arrayAux
        )
        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()

        val btnCrearAutosListView = findViewById<Button>(R.id.btn_crear_autos)
        btnCrearAutosListView
            .setOnClickListener {
                val idConcesionarioPadre = concesionarioSelect.id
                val intent =Intent(this, AutosForm::class.java)
                intent.putExtra("idConcesionario",idConcesionarioPadre)
                Log.i("TAG", "Vista de la lista padre ${idConcesionarioPadre}")
                startActivity(intent)
            }
        registerForContextMenu(listView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        val concesionarioSelect = intent.getSerializableExtra("Concesionario") as Concesionarios
        arrayAux = InformationDDBB.autosList.filter { it.codigoConcesionario == concesionarioSelect.id }.toMutableList()
        adaptador?.clear()
        adaptador?.addAll(arrayAux)
        adaptador?.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eliminarAuto(){
        val autoEliminado = arrayAux[idItemSeleccionado]
        InformationDDBB.autosList.remove(autoEliminado)
        arrayAux.removeAt(idItemSeleccionado)
        actualizarLista(adaptador)
        adaptador?.clear()
        adaptador?.addAll(arrayAux)
        adaptador?.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editAuto(){
        val autoSelect = arrayAux[idItemSeleccionado]
        val intent = Intent(this, AutosFormEdit::class.java)
        intent.putExtra("Fruta", autoSelect)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarLista(
        adaptador: ArrayAdapter<Autos>?
    ){
        if (adaptador != null) {
            adaptador.notifyDataSetChanged()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menuautos, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                //"${idItemSeleccionado}"
                editAuto()
                return true
            }
            R.id.mi_eliminar->{
                //"${idItemSeleccionado}"
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase);
        startActivity(intent);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar la fruta")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which -> eliminarAuto()
            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )
        val  dialogo = builder.create()
        dialogo.show()
    }

}