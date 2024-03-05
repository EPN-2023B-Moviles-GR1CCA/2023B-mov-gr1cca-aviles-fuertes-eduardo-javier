package com.example.examen02_firestore

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AutoListView : AppCompatActivity() {

    private var idItemSeleccionado = 0
    var arreglo:ArrayList<Autos> = arrayListOf()

    private var cId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_list_view)

        //Lista
        cId = intent.getStringExtra("autoId") ?: ""
        arreglo = ArrayList() // Inicializa la lista vacía, ya que Firestore manejará los datos

        val etCont = findViewById<TextView>(R.id.tv_autos)
        etCont.setText("ID concesionario: "+cId)

        val listView=findViewById<ListView>(R.id.lv_concesionario)
        val adaptador=ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //como se va a ver (XML)
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView=findViewById<Button>(
            R.id.btn_anadir_lv_auto)

        botonAnadirListView.setOnClickListener{
            val intent = Intent(this, AutoFormulario::class.java)
            intent.putExtra("concesionarioId", cId)
            startActivity(intent)
        }

        registerForContextMenu(listView)
        cargarDatosDesdeFirestore(adaptador)
    }

    // Cargar datos desde Firestore
    fun cargarDatosDesdeFirestore(adaptador: ArrayAdapter<Autos>){
        val db= Firebase.firestore
        val pRefUnico=db.collection("autos")
        arreglo.clear() //cada consulta se limpia el arreglo
        adaptador.notifyDataSetChanged()
        pRefUnico
            .whereEqualTo("concesionarioId", cId) // o cualquier otro campo que sea único
            .get()
            .addOnSuccessListener { //it (esto es lo q sea q llegue)
                for(auto in it){
                    auto.id //id quemado o autogenerado
                    anadirAArregloP(auto)
                }
                adaptador.notifyDataSetChanged()
            }
            .addOnFailureListener{   }
    }

    fun anadirAArregloP(p: QueryDocumentSnapshot){
        //ciudad.id
        val nuevo=Autos(
            p.data.get("id") as String?,
            p.data.get("nombre") as String?,
            p.data.get("cantidad") as Long?,
            p.data.get("precioUnitario") as Double?,
            p.data.get("esElectrico") as Boolean?,
            p.data.get("tipoAuto") as String?,
            p.data.get("concesionarioId") as String?
        )
        arreglo.add(nuevo)
    }

    //menu de contexto
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater=menuInflater
        inflater.inflate(R.menu.menuauto,menu)

        val info=menuInfo as AdapterView.AdapterContextMenuInfo
        val id=info.position
        idItemSeleccionado=id
    }

    //del item seleccionado
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar->{
                val auto = arreglo[idItemSeleccionado]
                val intent = Intent(this, AutoFormularioEditar::class.java)
                intent.putExtra(AutoFormularioEditar.EXTRA_AUTO, auto)
                startActivity(intent)
                true
            }
            R.id.mi_eliminar->{
                abrirDialogo()
                true
            }
            else-> super.onContextItemSelected(item)
        }
    }

    //accion al seleccionar Eliminar
    fun abrirDialogo(){
        val builder= AlertDialog.Builder(this)  //constructor de dialogo
        builder.setTitle("Desea eliminar? "+arreglo[idItemSeleccionado].nombre +arreglo[idItemSeleccionado].id)
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which->
                val auto = arreglo[idItemSeleccionado].id
                if (auto != null) {
                    eliminarRegistro(auto)
                }

            }
        )
        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo=builder.create()
        dialogo.show()
    }
    fun eliminarRegistro(id:String){
        val db=Firebase.firestore
        val ref=db.collection("autos")
        ref .document(id)
            .delete()
            .addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    arreglo.removeAt(idItemSeleccionado)
                } else {
                }
            }
            .addOnFailureListener {
            }
    }

    companion object {
        const val EXTRA_AUTO = "auto"
    }
}