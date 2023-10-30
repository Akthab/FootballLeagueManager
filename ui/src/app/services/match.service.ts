import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { catchError, map } from "rxjs/operators";
import { ErrorHandler } from "../errorhandlers/error-handler";

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private matchListUrl = "/match"
  private randomMatchUrl = "/match/random"

  constructor(private http: HttpClient) {}

  getMatch(){
    return this.http.get<any>(this.matchListUrl)
      .pipe(
        map(res => (res.response)
          .map( (data: any) => {
            return {
              team1Name: data.team1Name,
              team2Name: data.team2Name,
              team1Score: data.team1Score,
              team2Score: data.team2Score,
              dateTime: {
                day: data.dateTime.day,
                month: data.dateTime.month,
                year: data.dateTime.year,
              }
            }
          } )),
        catchError(err => ErrorHandler.handleError(err))
      );
  }

  createRandomMatch(){
    return this.http.get<any>(this.randomMatchUrl)
      .pipe(
        map( res => res.response),
        catchError(err => ErrorHandler.handleError(err))
      );
  }
}
