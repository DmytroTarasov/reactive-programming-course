import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

public class Task {

	public static void dynamicDemand(Flux<String> source, CountDownLatch countDownOnComplete) {

		source
				.subscribe(new BaseSubscriber<String>() {
					int toRequest = 1;
					int requested = 0;

					@Override
					protected void hookOnSubscribe(Subscription subscription) {
						request(toRequest);
					}

					@Override
					protected void hookOnNext(String value) {
						requested++;
						if (toRequest == requested) {
							requested = 0;
							toRequest *= 2;
							request(toRequest);
						}
					}

					@Override
					protected void hookFinally(SignalType type) {
						countDownOnComplete.countDown();
					}
				});
	}
}