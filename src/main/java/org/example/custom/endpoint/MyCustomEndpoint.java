package org.example.custom.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id="myCustomEndpoint")
@Component
public class MyCustomEndpoint {

    @ReadOperation
    public String customEndpoint() {
        //add your implementation here whatever you want capture and monitor
        return "This is a custom actuator endpoint!";
    }
}
