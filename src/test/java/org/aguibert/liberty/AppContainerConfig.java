/**
 *
 */
package org.aguibert.liberty;

import org.aguibert.testcontainers.framework.MicroProfileApplication;
import org.aguibert.testcontainers.framework.jupiter.SharedContainerConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;

/**
 * @author aguibert
 */
public class AppContainerConfig implements SharedContainerConfiguration {

    @Container
    public static MicroProfileApplication<?> app = new MicroProfileApplication<>()
                    .withNetwork(Network.SHARED)
                    .withAppContextRoot("/myservice")
                    .withEnv("MONGO_HOSTNAME", "testmongo")
                    .withEnv("MONGO_PORT", "27017")
                    .withMpRestClient(ExternalRestServiceClient.class, "http://mockserver:" + MockServerContainer.PORT);

    @Container
    public static MockServerContainer mockServer = new MockServerContainer()
                    .withNetwork(Network.SHARED)
                    .withNetworkAliases("mockserver");

    @Container
    public static GenericContainer<?> mongo = new GenericContainer<>("mongo:3.4")
                    .withNetwork(Network.SHARED)
                    .withNetworkAliases("testmongo");

    @Override
    public void startContainers() {
        // OPTIONAL: this method may be implemented to do custom instantiation/ordering of containers
        SharedContainerConfiguration.super.startContainers();
    }

}
