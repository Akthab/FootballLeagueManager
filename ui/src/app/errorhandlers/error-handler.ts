import {HttpErrorResponse} from "@angular/common/http";
import {NotFoundError} from "./not-found-error";
import {BadRequestError} from "./bad-request-error";
import {AppError} from "./app-error";
import {throwError} from "rxjs";

export class ErrorHandler{

  static handleError(err: HttpErrorResponse) {
    if (err.status === 404)
      throw new NotFoundError(err)
    else if (err.status === 400)
      throw new BadRequestError(err)
    else throw new AppError(err)
    return throwError(err)
  }

}
