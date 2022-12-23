import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

public class Task {

	public static Publisher<String> combineSeveralSources(Publisher<String> prefixPublisher,
			Publisher<String> wordPublisher,
			Publisher<String> suffixPublisher) {

		return Flux.combineLatest(prefixPublisher, wordPublisher, suffixPublisher,
				arr -> arr[0].toString() + arr[1].toString() + arr[2].toString());
	}
}