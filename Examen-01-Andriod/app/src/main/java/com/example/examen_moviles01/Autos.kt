package com.example.examen_moviles01

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable

@RequiresApi(Build.VERSION_CODES.O)
class Autos(
    var codigoConcesionario :Int?,
    var nombreAuto: String?,
    var cantidad: Int?,
    var precio: Double?,
    var electrico: Boolean?,
    var tipo: String?
):Serializable {

    override fun toString(): String {
        return "►${nombreAuto}\n\tCantidad: ${cantidad}\n\tPrecio: ${precio}\n\tEléctrico?: ${electrico}\n\tTipo(ej:SUV): ${tipo}"
    }
}