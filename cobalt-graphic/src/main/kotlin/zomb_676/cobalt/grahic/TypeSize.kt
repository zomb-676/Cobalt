package zomb_676.cobalt.grahic

@JvmInline
value class TypeSize<T : Number> private constructor(private val byteSize: Int) {
    companion object {
        val int = TypeSize<Int>(Int.SIZE_BYTES)
        val float = TypeSize<Float>(Float.SIZE_BYTES)
        val double = TypeSize<Double>(Double.SIZE_BYTES)
        val long = TypeSize<Long>(Long.SIZE_BYTES)
        val short = TypeSize<Short>(Short.SIZE_BYTES)
        val byte = TypeSize<Byte>(Byte.SIZE_BYTES)
    }

    operator fun times(num: Int): Int = byteSize * num
    infix fun size(num: Int): Int = byteSize * num

    operator fun get(num: Int): TypeSize<T> = TypeSize(byteSize * num)
    infix fun multi(num: Int): TypeSize<T> = TypeSize(byteSize * num)
}
