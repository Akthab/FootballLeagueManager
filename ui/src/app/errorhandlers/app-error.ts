import {HttpErrorResponse} from "@angular/common/http";

export class AppError {

  constructor(public originalError?: HttpErrorResponse) {
  }

  get errorMessage(){
    return this.originalError.error.response;
  }

}
