import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SearchReviewComponentComponent } from './components/search-review-component.component';
import { MovieReviewsListComponentComponent } from './components/movie-reviews-list-component.component';
import { ReviewService } from './review.service';
import { PostCommentComponentComponent } from './components/post-comment-component.component';
const appRoutes: Routes = [
  { path: '', component: SearchReviewComponentComponent },
  { path: 'list/:titleName', component: MovieReviewsListComponentComponent },
  { path: 'comment/:title', component: PostCommentComponentComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];
@NgModule({
  declarations: [
    AppComponent,
    SearchReviewComponentComponent,
    MovieReviewsListComponentComponent,
    PostCommentComponentComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
  ],
  providers: [ReviewService],
  bootstrap: [AppComponent],
})
export class AppModule {}
