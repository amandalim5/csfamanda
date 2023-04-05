package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	@Autowired
	MovieService movieService;

	@Autowired
	MovieRepository movieRepository;
	// TODO: Task 3, Task 4, Task 8

	// task 3
	@GetMapping(path = "/api/search")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<String> getMovies(@RequestParam(name = "query")String query){
		JsonArray theResult = null;
		List<Review> result = movieService.searchReviews(query);
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for(Review r: result){
			r.setCommentCount(movieRepository.countComments(r.getTitle()));
			arrBuilder.add(Review.toJson(r));
		}
		theResult = arrBuilder.build();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(theResult.toString());
	}

	@PostMapping(path = "/api/comment")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public ResponseEntity<String> postComment(@RequestParam(name = "cname") String cname, @RequestParam(name = "mname") String mname, @RequestParam(name = "theComment") String theComment, @RequestParam(name = "rating") Integer rating){
		// System.out.println(comment.getCname() + "================");
		Comment comment = new Comment();
		comment.setCname(cname);
		comment.setMname(mname);
		comment.setRating(rating);
		comment.setTheComment(theComment);
		String theId = movieRepository.insertComment(comment);
		return ResponseEntity.status(HttpStatus.OK).body(theId);
	}

	// @PostMapping(path = "/api/comment")
	// @CrossOrigin(origins = "*")
	// @ResponseBody
	// public ResponseEntity<String> postComment(@ModelAttribute Comment comment){
	// 	System.out.println(comment.getCname() + "================");
	// 	String theId = movieRepository.insertComment(comment);
	// 	return ResponseEntity.status(HttpStatus.OK).body(theId);
	// }


}
