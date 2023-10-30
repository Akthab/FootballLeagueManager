import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { catchError, map } from "rxjs/operators";
import { ErrorHandler } from "../errorhandlers/error-handler";

@Injectable({
  providedIn: 'root'
})
export class ClubService {
  private url = "/club";

  constructor(private http: HttpClient) {}

  getClubList(sortType){
    return this.http.get<any>(this.url + "/sort/" + sortType)
      .pipe(
        map(res => res.response),
        catchError( err => ErrorHandler.handleError(err))
      );
  }
}
