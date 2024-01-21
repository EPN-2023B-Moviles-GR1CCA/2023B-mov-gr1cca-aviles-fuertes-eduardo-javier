import java.io.Serializable

data class Concesionario(
    var nombre: String,
    var direccion: String,
    var numeroVendedores:Int,
    var vendeUsados: Boolean,
    var ingresoTotalLote: Double
) : Serializable