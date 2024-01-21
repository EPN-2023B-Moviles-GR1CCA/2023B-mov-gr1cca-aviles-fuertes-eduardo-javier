package com.example.examen_moviles01

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    val arrayAux = InformationDDBB.concesionariosList
    var idItemSeleccionado = 0

    var adaptador: ArrayAdapter<Concesionarios>? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view_concesionario)

        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arrayAux
        )

        listView.adapter = adaptador
        adaptador!!.notifyDataSetChanged()

        val btnCrearConcesionarioListView = findViewById<Button>(R.id.btn_crear_concesionario)
        btnCrearConcesionarioListView
            .setOnClickListener {
                irActividad(ConcesionariosForm::class.java)
            }
        registerForContextMenu(listView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        actualizarLista(adaptador!!)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eliminarConcesionario(){

        val iterador = InformationDDBB.autosList.iterator()
        while (iterador.hasNext()) {
            val auto = iterador.next()
            if (auto.codigoConcesionario == this.arrayAux[idItemSeleccionado].id) {
                iterador.remove()
            }
        }
        arrayAux.removeAt(idItemSeleccionado)
        actualizarLista(adaptador)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editarConcesionario(){
        val concesionarioSelect = arrayAux[idItemSeleccionado]
        val intent = Intent(this, ConcesionariosFormEdit :: class.java)
        intent.putExtra("Fruteria", concesionarioSelect)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun verAutosConcesionario(){
        val concesionarioSelect = arrayAux[idItemSeleccionado]
        val intent = Intent(this, AutosListView :: class.java)
        intent.putExtra("Concesionario",concesionarioSelect)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarLista(
        adaptador: ArrayAdapter<Concesionarios>?
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
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_editar ->{
                editarConcesionario()
                return true
            }
            R.id.mi_eliminar->{
                //"${idItemSeleccionado}"
                abrirDialogo()
                return true
            }
            R.id.mi_ver_concesionario->{
                //"${idItemSeleccionado}"
                verAutosConcesionario()
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
        builder.setTitle("Está seguro de eliminar el concesionario?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which -> eliminarConcesionario()
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