import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserActivityUtils {

	public static Mono<Product> findMostExpansivePurchase(Flux<Order> ordersHistory,
			ProductsCatalog productsCatalog) {

		return ordersHistory.flatMapIterable(Order::getProductsIds)
				.map(productsCatalog::findById)
				.reduce((product1, product2) ->
						product1.getPrice() > product2.getPrice() ? product1 : product2);
	}
}
