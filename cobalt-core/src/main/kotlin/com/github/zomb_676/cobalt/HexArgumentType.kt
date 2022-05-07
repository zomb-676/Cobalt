package com.github.zomb_676.cobalt

import com.mojang.brigadier.LiteralMessage
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.exceptions.CommandSyntaxException
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType


class HexArgumentType(private val minimum: Int = Int.MIN_VALUE, private val maximum: Int = Int.MAX_VALUE) :
    ArgumentType<Int> {

    companion object {
        private val example = mutableListOf("0xffffff", "0xff00ff")
        private val hexSynaxErrorType = DynamicCommandExceptionType { value ->
            LiteralMessage("hex number must begin witch 0x instead of $value")
        }
        private val readerExpectedStartOf0x = SimpleCommandExceptionType(LiteralMessage("expected start with 0x"))
        private val noHexInputType = SimpleCommandExceptionType(LiteralMessage("please enter number"))
    }

    @Throws(CommandSyntaxException::class)
    override fun parse(reader: StringReader): Int {
        var cursor = reader.cursor
        try {
            val first = reader.read()
            val second = reader.read()
            if (first != '0' || second != 'x') {
                reader.cursor = cursor
                throw hexSynaxErrorType.createWithContext(reader, "$first$second")
            }
        } catch (e: Exception) {
            throw readerExpectedStartOf0x.create()
        }
        cursor += 2
        val result :String
        try {
            result = reader.readString()
        }catch (e:Exception){
            throw noHexInputType.create()
        }
        val resultNum = Integer.parseInt(result,16)
        if (resultNum < minimum) {
            reader.cursor = cursor
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.integerTooLow().createWithContext(reader, result, minimum)
        }
        if (resultNum > maximum) {
            reader.cursor = cursor
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.integerTooHigh().createWithContext(reader, result, maximum)
        }
        return resultNum
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is IntegerArgumentType) return false
        val that = other
        return maximum == that.maximum && minimum == that.minimum
    }

    override fun hashCode(): Int {
        return 31 * minimum + maximum
    }

    override fun toString(): String {
        return if (minimum == Int.MIN_VALUE && maximum == Int.MAX_VALUE) {
            "integer()"
        } else if (maximum == Int.MAX_VALUE) {
            "integer($minimum)"
        } else {
            "integer($minimum, $maximum)"
        }
    }

    override fun getExamples(): MutableCollection<String> = example

}