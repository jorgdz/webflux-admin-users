package com.github.com.jorgdz.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootWebfluxApplication /*implements CommandLineRunner*/{

	//private static final Logger log = LoggerFactory.getLogger(SpringbootWebfluxApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxApplication.class, args);
	}
	/*
	@Override
	public void run(String... args) throws Exception {
		WebClient client = WebClient.create(restCountry);
		
		Instant start = Instant.now();
		
		List<Mono<String>> monoCountries = Stream.of(593, 594, 595)
				.map(i -> client.get().uri("/callingcode/{code}", i).retrieve().bodyToMono(String.class))
				.collect(Collectors.toList());
		
		Mono.when(monoCountries).block();
		
		for (int i = 593; i <= 595; i++) 
		{
			Mono<String> country = client.get().uri("/callingcode/{code}", i).retrieve().bodyToMono(String.class);
			
			country.subscribe(log::info);
		}
				
		timerLog(start);
	}
	
	private static void timerLog (Instant start)
	{
		log.debug("Tiempo transcurrido: " + Duration.between(start, Instant.now()).toMillis() + "ms");
	}*/
}
