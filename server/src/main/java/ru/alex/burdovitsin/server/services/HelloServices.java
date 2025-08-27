package ru.alex.burdovitsin.server.services;


import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;
import ru.alex.burdovitsin.server.HelloGrpc;
import ru.alex.burdovitsin.server.HelloRequest;
import ru.alex.burdovitsin.server.HelloResponse;


@GrpcService
public class HelloServices extends HelloGrpc.HelloImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String input = request.getInput();
        String output;
        if (!input.isEmpty()) {
            output = String.format("Detect input string '%s'", input);
        } else {
            output = "Not any input found!";
        }
        HelloResponse response = HelloResponse.newBuilder().setAnswer(output).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
