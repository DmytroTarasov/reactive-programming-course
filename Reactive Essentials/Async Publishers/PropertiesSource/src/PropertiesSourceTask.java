import reactor.core.publisher.Flux;

public class PropertiesSourceTask {

//	static Properties settings;
//
//	public static Iterable<Property<?>> createSequence() {
//		return settings.asList();
//	}
	static Properties settings;

	public static Flux<Property<?>> createSequence() {

		return Flux.fromIterable(settings.asList());
	}
}