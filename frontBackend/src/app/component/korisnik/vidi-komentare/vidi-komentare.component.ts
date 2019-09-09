import { Component, OnInit } from '@angular/core';
import { Utisak } from 'app/model/global-parameters/utisak';
import { UtisakService } from 'app/service/utisak/utisak.service';
import { AccommodationService } from 'app/service/accommodation/accommodation.service';
import { RecurseVisitor } from '@angular/compiler/src/i18n/i18n_ast';

@Component({
  selector: 'app-vidi-komentare',
  templateUrl: './vidi-komentare.component.html',
  styleUrls: ['./vidi-komentare.component.css']
})
export class VidiKomentareComponent implements OnInit {

  commentsToApprove: Utisak[] = [];
  p:any;

  constructor(private userReviewService: UtisakService, private accommodationService: AccommodationService) { }

  ngOnInit() {
    this.userReviewService.getUnapprovedUserReviews().subscribe(
      s => {
        this.commentsToApprove = s;
      }
    )
  }

  approveComment(review: Utisak){
    if(confirm("Approve comment?")) {
      review.komentarOdobren = true;
      this.userReviewService.updateReview(review).subscribe(
        s => {
          let pomReview: Utisak = new Utisak();
          this.commentsToApprove.forEach(element => {
            if(element.id === review.id){
              pomReview = element;
            }
          })
      
          this.commentsToApprove.splice(this.commentsToApprove.indexOf(pomReview), 1)
        }
      )
    }
  }

}
