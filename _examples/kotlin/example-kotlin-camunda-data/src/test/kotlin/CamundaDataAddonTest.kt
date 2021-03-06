package io.holunda.addons.camunda.data

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test
import java.util.*

class CamundaDataAddonTest {
    companion object {
        val FOO = object : Variable<String> {
            override val key: String
                get() = "foo"
            override val type: Class<String>
                get() = String::class.java
        }

        val BAR = object : Variable<String> {
            override val key: String
                get() = "bar"
            override val type: Class<String>
                get() = String::class.java
        }


    }

    @Test
    fun `can implement reader interface`() {
        val reader = MyVariableReader(mutableMapOf(FOO.key to "hello"))

        assertThat(reader.readRequired(FOO)).isEqualTo("hello")
        assertThatThrownBy { reader.readRequired(BAR) }.isInstanceOf(VariableNotFoundException::class.java)
    }

    @Test
    fun `implement writer interface`() {
        val writer = MyVariableReader(mutableMapOf())

        writer.write(FOO, "world")

        assertThat(writer.readRequired(FOO)).isEqualTo("world")
    }

    class MyVariableReader(val store: MutableMap<String, Any>) : VariableReader, VariableWriter {
        override fun <T : Any> read(variable: Variable<T>): Optional<T> = Optional.ofNullable(store[variable.key] as T?);

        override fun <T : Any> write(variable: Variable<T>, value: T): MyVariableReader {
            store.put(variable.key, value)
            return this
        }

    }
}