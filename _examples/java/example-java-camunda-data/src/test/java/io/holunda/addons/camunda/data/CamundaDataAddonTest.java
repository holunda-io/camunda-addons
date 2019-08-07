package io.holunda.addons.camunda.data;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CamundaDataAddonTest {

    private static final Variable<String> FOO = new Variable<String>() {
        @Override public String getKey() {
            return "foo";
        }

        @NotNull @Override public Class<String> getType() {
            return String.class;
        }
    };

    private static final Variable<String> BAR = new Variable<String>() {
        @Override public String getKey() {
            return "bar";
        }

        @NotNull @Override public Class<String> getType() {
            return String.class;
        }
    };

    @Test
    public void reader() {
        Map<String, Object> variables = Collections.singletonMap("foo", "hello");

        MyVariableReader reader = new MyVariableReader(variables);

        assertThat(reader.readRequired(FOO)).isEqualTo("hello");

        assertThatThrownBy(() -> reader.readRequired(BAR))
                .isInstanceOf(VariableNotFoundException.class);
    }

    public static class MyVariableReader implements VariableReader {

        private final Map<String, Object> store;

        public MyVariableReader(Map<String, Object> store) {
            this.store = store;
        }

        @Override public <T> Optional<T> read(Variable<T> variable) {
            return (Optional<T>) Optional.ofNullable(store.get(variable.getKey()));
        }

        @NotNull @Override public <T> T readRequired(@NotNull Variable<T> variable) {
            return read(variable).orElseThrow(() -> new VariableNotFoundException(variable));
        }
    }
}
