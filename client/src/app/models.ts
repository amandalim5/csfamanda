export interface Review {
  title: string;
  rating: number;
  byline: string;
  headline: string;
  summary: string;
  reviewURL: string;
  image: string;
  commentCount: number;
}

export interface Comment {
  mname: string;
  cname: string;
  rating: number;
  theComment: string;
}
