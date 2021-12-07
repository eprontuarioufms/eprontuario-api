package br.com.ufms.eprontuarioapi.domain.utils.validador;

import br.com.ufms.eprontuarioapi.domain.utils.validador.exception.ValidadorEProntuarioException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Validator<T> {
    private final T t;
    private final List<Throwable> exceptions = new ArrayList<>();

    private Validator(T t) {
        this.t = t;
    }

    public static <T> Validator<T> of(T t) {
        return new Validator<>(Objects.requireNonNull(t));
    }

    public Validator<T> validate(Predicate<T> validation, String message) {
        if (!validation.test(t)) {
            exceptions.add(new IllegalStateException(message));
        }
        return this;
    }

    public Validator<T> validateWithCustomException(Predicate<T> validation, Exception exception) {
        if (!validation.test(t)) {
            exceptions.add(exception);
        }
        return this;
    }

    public <U> Validator<T> validate(Function<T, U> projection, Predicate<U> validation, String message) {
        return validate(projection.andThen(validation::test)::apply, message);
    }

    public <U> Validator<T> validate(Boolean assertion, Exception exception) {
        if (!assertion) {
            exceptions.add(exception);
        }
        return this;
    }

    public T get() throws ValidadorEProntuarioException {
        if (exceptions.isEmpty()) {
            return t;
        }
        ValidadorEProntuarioException e = new ValidadorEProntuarioException();
        exceptions.forEach(e::addSuppressed);
        throw e;
    }

    public <U> Validator<T> validate(Function<T, U> projection, Predicate<U> validation, Exception e) {
        return validateWithCustomException(projection.andThen(validation::test)::apply, e);
    }

}
