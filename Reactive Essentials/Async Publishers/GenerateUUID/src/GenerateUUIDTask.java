import reactor.core.publisher.Mono;

public class GenerateUUIDTask {

	static UUIDGenerator uuidGenerator;

//	public static String generateRandomUUID() {
//		return uuidGenerator.secureUUID();
//	}
	public static Mono<String> generateRandomUUID() {
		return Mono.defer(() -> Mono.just(uuidGenerator.secureUUID()));
	}
}