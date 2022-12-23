import reactor.core.publisher.Flux;

public class Task {

    public static Flux<Long> createSequence() {
        return Flux
                .generate(
                        () -> STATE_ZERO,
                        (state, sink) -> {
                            sink.next(state.value);
                            if (state.iteration >= 19)
                                sink.complete();
                            return new State(
                                    state.iteration + 1,
                                    state.iteration == 0 ? 1 : state.value + state.previous.value,
                                    state);
                        }
                );
    }

    static class State {

        final State previous;
        final long value;
        final long iteration;

        State(long iteration, long value, State previous) {
            this.iteration = iteration;
            this.previous = previous;
            this.value = value;
        }
    }

    static final State STATE_ZERO = new State(0, 0, null);
    static final State STATE_ONE = new State(1, 1, STATE_ZERO);
}