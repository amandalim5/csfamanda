package ibf2022.batch1.csf.assessment.server.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Review;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;;

@Service
public class MovieService {
	public static final String REVIEW_API = "http://api.nytimes.com/svc/movies/v2/reviews/search.json";
	@Value("${REVIEW_KEY}")
	private String reviewkey;

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {
		String url = UriComponentsBuilder.fromUriString(REVIEW_API)
			.queryParam("api-key", reviewkey)
			.queryParam("query", query)
			.toUriString();	

		RequestEntity<Void> req = RequestEntity.get(url)
			.accept(MediaType.APPLICATION_JSON)
			.build();
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = null;


		try {
            resp = template.exchange(req, String.class);
        } catch (RestClientException ex) {
            ex.printStackTrace();
            return null;
        }

		String payload = resp.getBody();
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject reviewResp = reader.readObject();
        JsonArray jsonArr = reviewResp.getJsonArray("results");

		List<Review> result =  jsonArr.stream()
		.map(v -> v.asJsonObject())
		.map(Review::toReview)
		.toList();	
		return result;
	}

	public Optional<List<Review>> getReviews(String movieName){
		String url = UriComponentsBuilder.fromUriString(REVIEW_API)
			.queryParam("api-key", reviewkey)
			.queryParam("query", movieName)
			.toUriString();	

		RequestEntity<Void> req = RequestEntity.get(url)
			.accept(MediaType.APPLICATION_JSON)
			.build();
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> resp = null;


		try {
            resp = template.exchange(req, String.class);
        } catch (RestClientException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }

		String payload = resp.getBody();
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject reviewResp = reader.readObject();
        JsonArray jsonArr = reviewResp.getJsonArray("results");

		List<Review> result =  jsonArr.stream()
		.map(v -> v.asJsonObject())
		.map(Review::toReview)
		.toList();	

		if(result.size()==0){
			return Optional.empty();
		}
		return Optional.of(result);
	}

}
