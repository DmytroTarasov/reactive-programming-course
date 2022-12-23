import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;

public class Task {

    public static Publisher<String> replayLast3ElementsInHotFashion1(Flux<String> coldSource) {
        return coldSource.replay(3).autoConnect(0);
    }

    public static Publisher<String> replayLast3ElementsInHotFashion2(Flux<String> coldSource) {
        final Sinks.Many<String> replaySink = Sinks.many().replay().limit(3);

        Disposable disposable = coldSource.subscribe(
                next -> replaySink.emitNext(next, EmitFailureHandler.FAIL_FAST),
                error -> replaySink.emitError(error, EmitFailureHandler.FAIL_FAST),
                () -> replaySink.emitComplete(EmitFailureHandler.FAIL_FAST)
        );

        AtomicInteger subscribers = new AtomicInteger(0);
        return replaySink.asFlux()
                .doOnSubscribe(s -> subscribers.getAndIncrement())
                .doFinally(signal -> {
                    if (subscribers.decrementAndGet() == 0) disposable.dispose();
                });
    }
}