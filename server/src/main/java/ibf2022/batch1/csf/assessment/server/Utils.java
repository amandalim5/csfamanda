package ibf2022.batch1.csf.assessment.server;

import org.bson.Document;

import ibf2022.batch1.csf.assessment.server.models.Comment;

import static ibf2022.batch1.csf.assessment.server.Constants.*;

public class Utils {
    public static Document commentToDocument(Comment comment){
        Document document = new Document();
        document.put(FIELD_CNAME, comment.getCname());
        document.put(FIELD_MNAME, comment.getMname());
        document.put(FIELD_COMMENT, comment.getTheComment());
        document.put(FIELD_RATING, comment.getRating());
        return document;
    }
}
