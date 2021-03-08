package com.jetbrains.handson.httpapi.utils


import java.io.File
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun log(s: Any?){
    val log: String = "${s ?: "Lol"} ${System.lineSeparator()}"
    File("log.txt").appendText(s.toString())
}


//@ExperimentalSerializationApi
//@Serializer(forClass = Date::class)
//object DataSerializer: KSerializer<Date> {
//    private val df: DateFormat = SimpleDateFormat("dd/MM/yyyy")
//    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
//
//    override fun serialize(encoder: Encoder, value: Date) {
//        val result = df.format(value)
//        encoder.encodeString(result)
//    }
//
//    override fun deserialize(decoder: Decoder): Date {
//        val result = decoder.decodeString()
//        return SimpleDateFormat("dd/MM/yyyy").parse(result)
//    }
//}
