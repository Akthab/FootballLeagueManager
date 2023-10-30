import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { LeagueTableComponent } from "./league-table/league-table.component";
import { MatchViewComponent } from './match-view/match-view.component';
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [
    AppComponent,
    LeagueTableComponent,
    MatchViewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    RouterModule.forRoot([
      {
        path: 'table',
        component: LeagueTableComponent
      },
      {
        path: 'match',
        component: MatchViewComponent
      },
      {
        path: "**",
        component: LeagueTableComponent
      }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
