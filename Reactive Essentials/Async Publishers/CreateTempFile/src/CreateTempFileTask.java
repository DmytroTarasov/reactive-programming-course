import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.concurrent.Callable;

import reactor.core.publisher.Mono;

public class CreateTempFileTask {

//	public static File createTempFile(String prefix, String suffix) {
//		try {
// 			 return File.createTempFile(prefix, suffix);
// 		} catch(IOException e) {
// 			 throw new UncheckedIOException(e);
// 		}
//	}

	public static Mono<File> createTempFile(String prefix, String suffix) {
		return Mono.fromCallable(new Callable<File>() {
			@Override
			public File call() throws IOException {
				return File.createTempFile(prefix, suffix);
			}
		});
	}
}