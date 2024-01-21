package com.example.examen_moviles01

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable

@RequiresApi(Build.VERSION_CODES.O)
class Concesionarios (
    var id: Int,
    var nombre: String?,
    var direccion: String?,
    var cantidadVendedores: Int?,
    var vendeUsados: Boolean?,
    var totalPrecioLote: Double?

): Serializable {
    constructor(): this(
        0,
        null,
        null,
        null,
        null,
        null
    )

    override fun toString(): String {
        return "► Nombre Concesionario: ${nombre}\n\t Direccion: ${direccion}\n\t Cantidad de vendedores: ${cantidadVendedores}\n\t Vende usados? ${vendeUsados}\n\t  Precio total del lote: ${totalPrecioLote}"
    }

}