import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.function.TupleUtils;

public class Task {

	public static Publisher<String> zipSeveralSources(Publisher<String> prefixPublisher,
			Publisher<String> wordPublisher,
			Publisher<String> suffixPublisher) {
		return Flux.zip(prefixPublisher, wordPublisher, suffixPublisher)
				.map(i -> i.getT1() + i.getT2() + i.getT3());
	}
}