package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfiguration {

	@Bean
	@Profile("dev")
	public AmazonS3 s3Ninja() {
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAIOSFODNN7EXAMPLE",
				"wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");

		return AmazonS3ClientBuilder.standard()
				.withCredentials(new com.amazonaws.auth.AWSStaticCredentialsProvider(credentials))
				.withClientConfiguration(new ClientConfiguration())
				.withEndpointConfiguration(
						new com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration(
								"http://localhost:9444/s3", "us-east-1"))
				.withPathStyleAccessEnabled(true)
				.build();
	}
}