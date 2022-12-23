import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Task {

	public static Flux<Long> metricsTask(Flux<Long> flux) {

		return flux
				.metrics()
				.name("MyFlux")
				.subscribeOn(Schedulers.parallel())
				.publishOn(Schedulers.single())
				.log("After PublisherOn");
	}
}