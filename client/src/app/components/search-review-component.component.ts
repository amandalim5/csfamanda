import { Component, OnInit, OnDestroy } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review-component',
  templateUrl: './search-review-component.component.html',
  styleUrls: ['./search-review-component.component.css'],
})
export class SearchReviewComponentComponent implements OnInit, OnDestroy {
  searchForm!: FormGroup;
  titleName?: string;
  constructor(private fb: FormBuilder, private router: Router) {}

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      title: this.fb.control<string>('', [
        Validators.required,
        // Validators.minLength(2),
        this.nonWhiteSpace,
      ]),
    });
  }
  ngOnDestroy(): void {}

  processForm() {
    const titleName = this.searchForm.value['title'];
    this.router.navigate(['/list', titleName]);
  }

  nonWhiteSpace = (ctrl: AbstractControl) => {
    if (ctrl.value.trim().length > 1) {
      return null;
    }
    return { nonWhiteSpace: true } as ValidationErrors;
  };
}
