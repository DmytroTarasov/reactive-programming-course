import reactor.core.publisher.Mono;

public class Task {

	public static Mono<String> grabDataFromTheGivenContext(Object key) {
		return Mono
				.deferContextual(ctxView -> {
					if (ctxView.hasKey(key)) {
						return Mono.just(ctxView.get(key));
					}
					return Mono.error(new IllegalArgumentException("Context doesn't have the provided key"));
				});
	}
}