import { Component, OnInit } from '@angular/core';
import { MatchService } from "../services/match.service";
import { Match } from "../entities/match";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import {AppError} from "../errorhandlers/app-error";
import {NotFoundError} from "../errorhandlers/not-found-error";
import {BadRequestError} from "../errorhandlers/bad-request-error";

@Component({
  selector: 'app-match-view',
  templateUrl: './match-view.component.html',
  styleUrls: ['./match-view.component.css']
})
export class MatchViewComponent implements OnInit {
  matchListDisplay: Match[]
  matchList: Match[]
  matchListByDate: Match[]
  createdMatch: Match
  modalHeading: String;
  modalBody: String;

  constructor(private service: MatchService) { }

  searchMatchForm = new FormGroup({
    date: new FormControl('',[
      Validators.required
    ])
  })

  ngOnInit(): void {
    this.service.getMatch()
      .subscribe(Response => {
        this.matchList = Response;
        this.matchListDisplay = Response
        console.log(Response);
      },
        ((error : AppError) => {
          if (error instanceof NotFoundError)
            alert("Page Not Found");
          else
            alert(error.errorMessage)
            console.log(error.originalError)
        }));
  }

  searchMatch() {
    let getDate: String = this.searchMatchForm.value.date;
    let dateArr: any = getDate.split("-");
    let date = {
      day: parseInt(dateArr[2]),
      month: parseInt(dateArr[1]),
      year: parseInt(dateArr[0])
    }
    this.matchListByDate = this.matchList
      .filter(match =>
        match.dateTime.day == date.day &&
        match.dateTime.month == date.month &&
        match.dateTime.year == date.year)

    this.matchListDisplay = this.matchListByDate;
  }

  createRandomMatch() {
    this.service.createRandomMatch()
      .subscribe( Response => {
        //takes the response from backend and shows in modal
        this.createdMatch = Response;
        this.modalHeading = "Success!";
        this.modalBody = this.matchToString(this.createdMatch);
        console.log(Response);
        //clears all searches and adds the match to match list
        this.clear()
        this.matchList.push(this.createdMatch)
        },(error: AppError) => {
          this.modalHeading = "Error !";
          if (error instanceof NotFoundError)
            this.modalBody = "Page was not found";
          else if (error instanceof BadRequestError)
            this.modalBody = error.errorMessage;
          else
            this.modalBody = error.errorMessage;
          console.log(error.originalError)
        }
      )
  }

  clear() {
    this.matchListDisplay = this.matchList;
    this.searchMatchForm.get('date').setValue(null)
  }

  matchToString(match: Match): String{
    return "["+match.dateTime.day + "/" + match.dateTime.month + "/" + match.dateTime.year +"] "
      + match.team1Name + "  " + match.team1Score + " : " + match.team2Score + "  " + match.team2Name
  }

}
