import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val concesionarioCRUD = ConsecionarioCRUD()
    val autosCRUD = AutosCRUD()
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
    val titulo = "CRUD EN CONSOLA PARA MODIFICAR PARAMETROS DE AUTOMOVILES"


    loop@ while (true) {
        println("""""")
        println(titulo)
        println("""""")
        println("1. Crear un nuevo concesionario")
        println("""""")
        println("2. Crear nuevo(s) auto(s)")
        println("""""")
        println("3. Listar todos los concesionarios")
        println("""""")
        println("4. Listar todos los autos")
        println("""""")
        println("5. Actualizar información de un concesionario")
        println("""""")
        println("6. Actualizar información de un auto")
        println("""""")
        println("7. Eliminar un concesionario")
        println("""""")
        println("8. Eliminar un auto")
        println("""""")
        println("9. Exit")
        println("""""")
        println("Seleccione la funcionalidad:")

        when (scanner.nextInt()) {
            1 -> {
                scanner.nextLine()
                println("Ingrese el nombre del nuevo concesionario:")
                val nombre = scanner.nextLine()
                println("Ingrese la direccion del concesionario:")
                val direccion = scanner.nextLine()
                println("Ingrese el número de vendedores activos:")
                val numeroVendedores = scanner.nextInt()
                scanner.nextLine()
                println("Vende autos usados? (true/false):")
                val vendeUsados = scanner.nextBoolean()
                scanner.nextLine()
                println("Valor total de ingreso del lote de autos:")
                val ingresoTotalLote = scanner.nextDouble()
                scanner.nextLine()

                val consecionario = Concesionario(nombre, direccion, numeroVendedores, vendeUsados, ingresoTotalLote)
                concesionarioCRUD.crearConcesionario(consecionario)
            }
            2 -> {
                scanner.nextLine()
                println("Ingrese el nombre del nuevo auto:")
                val nombreAuto = scanner.nextLine()
                println("Ingrese la cantidad de autos del nuevo lote:")
                val cantidadAutos = scanner.nextInt()
                println("Ingrese el precio del auto:")
                val precioAuto = scanner.nextDouble()
                scanner.nextLine()
                println("La auto es eléctrico? (true/false):")
                val electrico = scanner.nextBoolean()
                scanner.nextLine()
                println("Ingrese el tipo auto (ejemplo:SUV)")
                val tipoAuto = scanner.nextLine()
                scanner.nextLine()

                val autos = Autos(nombreAuto, cantidadAutos, precioAuto, electrico, tipoAuto)
                autosCRUD.crearAutos(autos)
            }
            3 -> {
                println("Concesionarios existentes:")
                concesionarioCRUD.listarConcesionarios()
            }
            4 -> {
                println("Autos existentes:")
                autosCRUD.listarAutos()
            }
            5 -> {
                println("Ingrese el codigo del concesionario para actualizar:")
                val index = scanner.nextInt()
                scanner.nextLine()

                val concesionarios = concesionarioCRUD.cargarConcesionarios()
                if (index >= 0 && index < concesionarios.size) {
                    println("Ingrese el nombre del concesionario:")
                    val nombre = scanner.nextLine()
                    println("Ingrese la direccion:")
                    val direccion = scanner.nextLine()
                    println("Ingrese el número de vendedores activos:")
                    val numeroVendedores = scanner.nextInt()
                    scanner.nextLine()
                    println("Vende autos usados? (true/false):")
                    val vendeUsados = scanner.nextBoolean()
                    scanner.nextLine()
                    println("Valor total de ingreso del lote de autos:")
                    val ingresoTotalLote = scanner.nextDouble()
                    scanner.nextLine()

                    val concesionario= Concesionario(nombre, direccion, numeroVendedores, vendeUsados, ingresoTotalLote)
                   concesionarioCRUD.actualizarConcesionario(index, concesionario)
                } else {
                    println("El codigo ingresado es inválido.")
                }
            }
            6 -> {
                println("Ingrese el código del auto para actualizar los datos:")
                val index = scanner.nextInt()
                scanner.nextLine()

                val autos = autosCRUD.cargarAutos()
                if (index >= 0 && index < autos.size) {
                    println("Ingrese el nombre del auto:")
                    val nombreAuto = scanner.nextLine()
                    println("Ingrese la cantidad de autos:")
                    val cantidadAutos = scanner.nextInt()
                    println("Ingrese el precio del auto:")
                    val precioAuto = scanner.nextDouble()
                    scanner.nextLine()
                    println("Es eléctrico? (true/false):")
                    val electrico = scanner.nextBoolean()
                    scanner.nextLine()
                    println("Ingrese el tipo de auto (ej: SUV)")
                    val tipoAuto = scanner.nextLine()
                    scanner.nextLine()

                    val autos = Autos(nombreAuto, cantidadAutos, precioAuto, electrico, tipoAuto)
                    autosCRUD.actualizarAutos(index, autos)
                } else {
                    println("El código del auto es inválido.")
                }
            }
            7 -> {
                println("Ingrese el código del concesionario para eliminarlo:")
                val index = scanner.nextInt()
                scanner.nextLine()

                val concesionario = concesionarioCRUD.cargarConcesionarios()
                if (index >= 0 && index < concesionario.size) {
                    concesionarioCRUD.eliminarConcesionario(index)
                    println("El concesionario fue eliminado!!!")
                } else {
                    println("El código ingresado es inválido.")
                }
            }
            8 -> {
                println("Ingrese el código del auto para eliminarlo:")
                val index = scanner.nextInt()
                scanner.nextLine()

                val autos = autosCRUD.cargarAutos()
                if (index >= 0 && index < autos.size) {
                    autosCRUD.eliminarAutos(index)
                    println("El auto fue eliminado!!!")
                } else {
                    println("El código del auto es inválido.")
                }
            }
            9 -> break@loop
            else -> println("Digitar bien el número de la opción!!!")
        }
    }

}
