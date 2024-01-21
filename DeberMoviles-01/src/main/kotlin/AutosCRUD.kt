import java.io.*

class AutosCRUD {
    private val archivo = File("autos.txt")

    fun crearAutos(auto: Autos) {
        val autos = cargarAutos()
        autos.add(auto)
        guardarAutos(autos)
        println("Auto creado con éxito!!!")
    }

    fun listarAutos() {
        val autos = cargarAutos()
        if (autos.isNotEmpty()) {
            for ((index, auto) in autos.withIndex()) {
                println("=== Auto ${index + 1} ===")
                println(auto)
            }
        } else {
            println("No existen autos registrados aún!!!")
        }
    }

    fun actualizarAutos(index: Int, nuevaAuto: Autos) {
        val autos = cargarAutos()
        if (index >= 0 && index < autos.size) {
            autos[index] = nuevaAuto
            guardarAutos(autos)
            println("Auto actualizado con éxito!!!")
        }
    }

    fun eliminarAutos(index: Int) {
        val autos = cargarAutos()
        if (index >= 0 && index < autos.size) {
            autos.removeAt(index)
            guardarAutos(autos)
            println("Auto eliminado con éxito!!!")
        } else {
            println("Código inválido!!!")
        }
    }

    fun cargarAutos(): MutableList<Autos> {
        val autos: MutableList<Autos> = mutableListOf()
        if (archivo.exists()) {
            archivo.bufferedReader(Charsets.UTF_8).use { reader ->
                reader.lineSequence().forEach { line ->
                    val campos = line.split(",")
                    if (campos.size >= 4) {
                        val nombreAuto = campos[0]
                        val cantidadAutos = campos[1].toInt()
                        val precioAuto = campos[2].toDouble()
                        val electrico = campos[3].toBoolean()
                        val tipoAuto = campos[4]
                        val auto = Autos(nombreAuto, cantidadAutos, precioAuto, electrico, tipoAuto)
                        autos.add(auto)
                    }
                }
            }
        }
        return autos
    }

    private fun guardarAutos(autos: List<Autos>) {
        archivo.bufferedWriter(Charsets.UTF_8).use { writer ->
            for (auto in autos) {
                writer.write("${auto.nombreAuto},${auto.cantidadAutos},${auto.precioAuto},${auto.electrico},${auto.tipoAuto}")
                writer.newLine()
            }
        }
    }
}
