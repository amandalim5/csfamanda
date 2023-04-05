import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Subject, firstValueFrom, lastValueFrom, take } from 'rxjs';
import { query } from '@angular/animations';
import { Comment, Review } from './models';
const GAME_API = 'https://amandaworkwork-production.up.railway.app/api/search';
const COMMENT_API =
  'https://amandaworkwork-production.up.railway.app/api/comment';

@Injectable()
export class ReviewService {
  constructor(private httpClient: HttpClient) {}
  onSearch = new Subject();
  theSearchedTitle: string = '';
  onComment = new Subject();

  getReviews(title: string) {
    this.theSearchedTitle = title;
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=utf-8'
    );
    let queryParams = new HttpParams().set('query', title);
    let result$ = this.httpClient
      .get(`${GAME_API}`, { params: queryParams, headers: headers })
      .pipe(take(1));

    return lastValueFrom(result$)
      .then((data: any) => {
        const reviews = data as Review[];
        return reviews;
      })
      .then((data) => {
        this.onSearch.next(data);
        return data;
      })
      .catch((message: any) => {
        console.info(message);
        this.onSearch.error(message);
        return () => {};
      });
  }

  postComment(comment: Comment) {
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/json; charset=utf-8'
    );
    const body = JSON.stringify(comment);
    console.info('this is the body ' + body);
    let queryParams = new HttpParams()
      .set('cname', comment.cname)

      .set('mname', comment.mname)
      .set('rating', comment.rating)
      .set('theComment', comment.theComment);

    console.info(comment.cname + '+++++++++++++++++++');
    let result$ = this.httpClient
      .post<Comment>(COMMENT_API, { params: queryParams, headers: headers })
      .pipe(take(1));
    return lastValueFrom(result$)
      .then((data: any) => {
        const id = data as string;
        this.onComment.next(id);
        return id;
      })
      .catch((message: any) => {
        console.info(message);
        this.onComment.error(message);
        return () => {};
      });
  }
}
