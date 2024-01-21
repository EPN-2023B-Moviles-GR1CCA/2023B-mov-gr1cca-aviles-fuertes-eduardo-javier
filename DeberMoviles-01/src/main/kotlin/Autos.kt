
import java.io.Serializable

data class Autos(
    var nombreAuto: String,
    var cantidadAutos: Int,
    var precioAuto: Double,
    var electrico: Boolean,
    var tipoAuto: String
) : Serializable
