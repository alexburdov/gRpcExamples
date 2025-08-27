package ru.alex.burdovitsin.client.clients;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.alex.burdovitsin.client.HelloGrpc;
import ru.alex.burdovitsin.client.HelloRequest;
import ru.alex.burdovitsin.client.HelloResponse;

@Service
public class HelloGrpcClient {

    @Value("${hello.grpc.server.name}")
    private String serverName = "localhost";

    @Value("${hello.grpc.server.port}")
    private int serverPort = 9090;

    private final HelloGrpc.HelloBlockingStub blockingStub;

    public HelloGrpcClient() {
        ManagedChannel channel
                = ManagedChannelBuilder.forAddress(serverName, serverPort)
                .usePlaintext()
                .build();
        this.blockingStub = HelloGrpc.newBlockingStub(channel);
    }

    public String sayHello(String name) {
        HelloRequest request = HelloRequest.newBuilder().setInput(name).build();
        HelloResponse response = blockingStub.sayHello(request);
        return response.getAnswer();
    }
}
