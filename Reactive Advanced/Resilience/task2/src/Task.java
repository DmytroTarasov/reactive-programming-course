import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Task {

	public static Publisher<String> fallbackOnError(Flux<String> publisher, String fallback) {
		return publisher.onErrorResume(e -> Mono.just(fallback));
	}
}