import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment } from '../models';
import { ReviewService } from '../review.service';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-post-comment-component',
  templateUrl: './post-comment-component.component.html',
  styleUrls: ['./post-comment-component.component.css'],
})
export class PostCommentComponentComponent implements OnInit {
  commentForm!: FormGroup;
  title = '';
  subscription!: Subscription;
  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private reviewSvc: ReviewService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.commentForm = this.fb.group({
      cname: this.fb.control<string>('', [
        Validators.required,
        this.nonWhiteSpace,
      ]),
      rating: this.fb.control<number>(1, [
        Validators.required,
        Validators.min(1),
        Validators.max(5),
      ]),
      comment: this.fb.control<string>('', [Validators.required]),
    });
    this.title = this.activatedRoute.snapshot.params['title'];
  }
  processForm() {
    const c = {} as Comment;
    c.cname = this.commentForm.value['cname'];
    console.info("this is the comment's name" + c.cname);
    c.rating = this.commentForm.value['rating'];
    c.theComment = this.commentForm.value['comment'];
    c.mname = this.title;
    this.subscription = this.reviewSvc.onComment.subscribe((data) => {
      console.info(data);
    });
    this.reviewSvc.postComment(c);

    this.router.navigate(['/list', this.reviewSvc.theSearchedTitle]);
  }

  nonWhiteSpace = (ctrl: AbstractControl) => {
    if (ctrl.value.trim().length > 2) {
      return null;
    }
    return { nonWhiteSpace: true } as ValidationErrors;
  };

  goBack() {
    this.commentForm.reset();
    this.router.navigate(['/list', this.reviewSvc.theSearchedTitle]);
  }
}
