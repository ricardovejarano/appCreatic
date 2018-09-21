package com.example.ricardo.pruebatecnicaareamovil.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserRealm(@PrimaryKey var id: Long = 0,
                     var name: String = "",
                     var url: String = ""): RealmObject()