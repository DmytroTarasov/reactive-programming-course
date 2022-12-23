import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.List;

public class CreateSequenceTask {

//	public static Iterable<Integer> createSequence() {
//		List<Integer> sequence = new ArrayList<>();
//		for (int i = 0; i < 20; i++) {
//			sequence.add(i);
//		}
//		return sequence;
//	}

	public static Flux<Integer> createSequence() {
		return Flux.range(0, 20);
	}
}