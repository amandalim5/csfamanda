import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { ReviewService } from '../review.service';
import { Review } from '../models';

@Component({
  selector: 'app-movie-reviews-list-component',
  templateUrl: './movie-reviews-list-component.component.html',
  styleUrls: ['./movie-reviews-list-component.component.css'],
})
export class MovieReviewsListComponentComponent implements OnInit, OnDestroy {
  titleName = '';
  titles: Review[] = [];
  subscription!: Subscription;
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private reviewSvc: ReviewService
  ) {}
  observer = {
    next: (value: Review[]) => {
      console.info('this is the info ', value[0].title);
      this.titles = value;
    },
    error: (value: any) => {
      console.info('there was error', value);
    },
  };

  ngOnInit(): void {
    this.titleName = this.activatedRoute.snapshot.params['titleName'];
    this.subscription = this.reviewSvc.onSearch.subscribe((data) => {
      this.titles = data as Review[];
    });
    this.reviewSvc.getReviews(this.titleName);
  }
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  // will not go to link if it does not exist...
  goToLink(i: number) {
    if (this.titles[i].reviewURL != 'not value') {
      window.open(this.titles[i].reviewURL);
    }

    // window.open('https://github.com/amandalim5');
  }
}
