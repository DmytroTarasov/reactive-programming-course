import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ValidationTask {

//	public static boolean validate(Duration duration) {
//		return !duration.isNegative() && !duration.isZero();
//	}

	public static Mono<Void> validate(Duration duration) {
		if (duration.isNegative() || duration.isZero()) {
			return Mono.error(new IllegalArgumentException("Duration cannot be negative or equal to 0"));
		}
		return Mono.empty();
	}
}