package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.Utils;
import ibf2022.batch1.csf.assessment.server.models.Comment;
import static ibf2022.batch1.csf.assessment.server.Constants.*;
@Repository
public class MovieRepository {
	@Autowired
	MongoTemplate template;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//

	// public int countComments(Object param) {
	// 	return 0;
	// }

	// Mongo query:
	// db.comments.find({title:'movie-title'})
	public int countComments(String title) {
		Criteria criteria = Criteria.where("mname").is(title);
		Query query = Query.query(criteria);
		List<Document> result = template.find(query, Document.class, "comments");

		return result.size();
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//


	// Mongo insert:
	// db.comments.insert({cname:"poster's name", mname:"movie name", comment:"their comments", rating: 5})
	public String insertComment(Comment comment){
		Document toInsert = Utils.commentToDocument(comment);
		Document newDoc = template.insert(toInsert, "comments");
		String stringId = newDoc.get(FIELD_UNDERSCORE_ID).toString();
		System.out.println("the id of the document in mongo is: " + stringId);	
		return stringId;
	}
}
