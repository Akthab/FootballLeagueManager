import { Component, OnInit } from '@angular/core';
import { ClubService } from "../services/club.service";
import { FootballClub } from "../entities/football-club";
import { AppError } from "../errorhandlers/app-error";
import { NotFoundError } from "../errorhandlers/not-found-error";
import { BadRequestError } from "../errorhandlers/bad-request-error";

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})
export class LeagueTableComponent implements OnInit {
  footBallClubList: FootballClub[]

  constructor(private services: ClubService) { }

  getList(sortType: String){
    this.services.getClubList(sortType)
      .subscribe(Response => {
        this.footBallClubList = Response;
        console.log(this.footBallClubList)
      },(error: AppError) => {
        if (error instanceof NotFoundError)
          alert("Page not Found");
        else if (error instanceof BadRequestError)
          alert(error.errorMessage)
        else
          alert(error.errorMessage)
        console.log(error.originalError)
      });
  }

  ngOnInit(): void {
    this.getList('points')
  }

  sortByPoints() {
    this.getList('points');
  }

  sortByWins() {
    this.getList("wins")
  }

  sortByGF() {
    this.getList("gf")
  }


}
