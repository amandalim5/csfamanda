package ibf2022.batch1.csf.assessment.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short 
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setRating(String rating) { this.rating = rating; }
	public String getRating() { return this.rating; }

	public void setByline(String byline) { this.byline = byline; }
	public String getByline() { return this.byline; }

	public void setHeadline(String headline) { this.headline = headline; }
	public String getHeadline() { return this.headline; }

	public void setSummary(String summary) { this.summary = summary; }
	public String getSummary() { return this.summary; }

	public void setReviewURL(String reviewURL) { this.reviewURL = reviewURL; }
	public String getReviewURL() { return this.reviewURL; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }
	public boolean hasImage() { return null != this.image; }

	public void setCommentCount(int commentCount) { this.commentCount = commentCount; };
	public int getCommentCount() { return this.commentCount; }


	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}

	public static Review toReview(JsonObject obj){
		Review review = new Review();
		review.setTitle(getValue("display_title",obj));
		review.setRating(getValue("mpaa_rating", obj));
		review.setByline(getValue("byline", obj));
		review.setHeadline(getValue("headline", obj));
		review.setSummary(getValue("summary_short", obj));
		review.setReviewURL(getValue("link.url", obj));
		review.setImage(getValue("multimedia.src", obj));
		return review;
	}

	private static String getValue(String fn, JsonObject o) {

        if (o.containsKey(fn) && !o.isNull(fn))
            return o.getString(fn);
        return "not value";
    }

	public static JsonObject toJson(Review r){
		return Json.createObjectBuilder()
					.add("title", r.getTitle())
					.add("rating", r.getRating())
					.add("byline", r.getByline())
					.add("headline", r.getHeadline())
					.add("summary",r.getSummary())
					.add("reviewURL",r.getReviewURL())
					.add("image",r.getImage())
					.add("commentCount", r.getCommentCount())
					.build();
	}
}
