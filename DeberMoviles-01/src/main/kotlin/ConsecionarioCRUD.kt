import java.io.*

class ConsecionarioCRUD {
    private val archivo = File("concesionarios.txt")

    fun crearConcesionario(concesionario: Concesionario) {
        val concesionarios = cargarConcesionarios()
        concesionarios.add(concesionario)
        guardarConcesionarios(concesionarios)
        println("Concesionario creado con éxito!!!")
    }

    fun listarConcesionarios() {
        val concesionarios = cargarConcesionarios()
        if (concesionarios.isNotEmpty()) {
            for ((index, concesionario) in concesionarios.withIndex()) {
                println("=== Concesionario ${index + 1} ===")
                println(concesionario)
            }
        } else {
            println("No exiten concesionarios registrados aún!!!")
        }
    }

    fun actualizarConcesionario(index: Int, nuevoConcesionario: Concesionario) {
        val concesionarios = cargarConcesionarios()
        if (index >= 0 && index < concesionarios.size) {
            concesionarios[index] = nuevoConcesionario
            guardarConcesionarios(concesionarios)
            println("Concesioanrio actualizada con éxito!!!")
        }
    }

    fun eliminarConcesionario(index: Int) {
        val concesionarios = cargarConcesionarios()
        if (index >= 0 && index < concesionarios.size) {
            concesionarios.removeAt(index)
            guardarConcesionarios(concesionarios)
            println("COncesionario eliminado con éxito!!!")
        } else {
            println("Código inválido.")
        }
    }

    fun cargarConcesionarios(): MutableList<Concesionario> {
        val concesionarios: MutableList<Concesionario> = mutableListOf()
        if (archivo.exists()) {
            archivo.bufferedReader(Charsets.UTF_8).use { reader ->
                reader.lineSequence().forEach { line ->
                    val campos = line.split(",")
                    if (campos.size >= 4) {
                        val nombre = campos[0]
                        val direccion = campos[1]
                        val numeroVendedores = campos[2].toInt()
                        val vendeUsados = campos[3].toBoolean()
                        val ingresoTotalLote = campos[4].toDouble()
                        val concesionario = Concesionario(nombre, direccion, numeroVendedores, vendeUsados, ingresoTotalLote)
                        concesionarios.add(concesionario)
                    }
                }
            }
        }
        return concesionarios
    }

    private fun guardarConcesionarios(concesionarios: List<Concesionario>) {
        archivo.bufferedWriter(Charsets.UTF_8).use { writer ->
            for (concesionario in concesionarios) {
                writer.write("${concesionario.nombre},${concesionario.direccion},${concesionario.numeroVendedores},${concesionario.vendeUsados},${concesionario.ingresoTotalLote}")
                writer.newLine()
            }
        }
    }
}



