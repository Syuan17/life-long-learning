package nio02.kimmking.gateway.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Syuan
 * @Date: 2021/8/22 11:40 下午
 */
public class RobinHtppEndpointRouter implements HttpEndpointRouter {

    private static final AtomicInteger index = new AtomicInteger(0);

    @Override
    public String route(List<String> endpoints) {
        index.compareAndSet(Integer.MAX_VALUE, 0);
        int i = index.getAndIncrement();
        return endpoints.get(i % endpoints.size());
    }
}
