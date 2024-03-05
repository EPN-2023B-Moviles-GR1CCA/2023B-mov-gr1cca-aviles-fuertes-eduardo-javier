package com.example.examen02_firestore

import android.os.Parcel
import android.os.Parcelable


class Autos(
    public var id: String?,
    public var nombre: String?,
    public var cantidad: Long?,
    public var precioUnitario: Double?,
    public var esElectrico: Boolean?,
    public var tipoAuto: String?,
    public var concesionarioId: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString()
    )

    override fun toString(): String {
        return "${nombre} - ${cantidad} - ${precioUnitario} - ${esElectrico} - ${tipoAuto}"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, p1: Int) {
        parcel.writeString(id)
        parcel.writeString(nombre)
        parcel.writeValue(cantidad)
        parcel.writeValue(precioUnitario)
        parcel.writeValue(esElectrico)
        parcel.writeValue(tipoAuto)
        parcel.writeString(concesionarioId)
    }

    companion object CREATOR : Parcelable.Creator<Autos> {
        override fun createFromParcel(parcel: Parcel): Autos {
            return Autos(parcel)
        }

        override fun newArray(size: Int): Array<Autos?> {
            return arrayOfNulls(size)
        }
    }
}