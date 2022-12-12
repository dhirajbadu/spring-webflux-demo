package com.upwork.reactive;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class EmployeeController {

    @GetMapping(value = "/foo", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Foo> getEmployeesAsync(){
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .map(i->new Foo(UUID.randomUUID(), "Foo "+i));
    }

    @AllArgsConstructor
    class Foo{
        public UUID id;

        public String name;
    }
}
