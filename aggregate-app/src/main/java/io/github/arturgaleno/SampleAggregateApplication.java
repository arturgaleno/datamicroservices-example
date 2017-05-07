package io.github.arturgaleno;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.aggregate.AggregateApplicationBuilder;

@SpringBootApplication
public class SampleAggregateApplication {

	public static void main(String[] args) {
        new AggregateApplicationBuilder()
			.from(SourceApplication.class).namespace("source").args("--fixedDelay=5000")
			.via(ProcessorApplication.class).namespace("processor")
//			.to(SinkApplication.class).namespace("sink").args("--debug=true")
			.run(args);
	}
}