package com.example.examen_moviles01

    import android.os.Build
    import androidx.annotation.RequiresApi

    class InformationDDBB {

        @RequiresApi(Build.VERSION_CODES.O)
        companion object {
            val concesionariosList: MutableList<Concesionarios> = mutableListOf()
            val autosList: MutableList<Autos> = mutableListOf()


            init {

                agregarConcesionarios(Concesionarios(
                    1,
                    "Star Motors",
                    "Galo Plaza y Mariana de Jesús",
                    10,
                    false,
                    350000.00)
                )

                agregarAutos(
                    Autos(
                        1,
                        "Mazda CX30",
                        20,
                        29990.00,
                        false,
                        "SUV")
                )

                agregarAutos(
                    Autos(
                        1,
                        "Hyundai Kona",
                        22,
                        32990.00,
                       true,
                        "SUV e")
                )

            }

            fun agregarConcesionarios(concesionario: Concesionarios) {
                concesionariosList.add(concesionario)
            }


            fun agregarAutos(auto: Autos) {
                autosList.add(auto)
            }

        }
    }
