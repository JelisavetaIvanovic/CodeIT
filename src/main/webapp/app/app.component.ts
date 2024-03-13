import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { GameService } from './dashboard/serveces/game.service';
import { ArmyData } from './dashboard/model/army.model';
import { GameLogService } from './dashboard/serveces/gameLog.service';
import { Subscription } from 'rxjs';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, MatFormFieldModule, MatSelectModule, MatInputModule, CommonModule, MatButtonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit, OnDestroy{
  private subscription?: Subscription;
  selectedValue: string = "";
  listOfGames: {id: string; army: string[] }[];
  selectedGame: string;
  armyData: ArmyData;

  constructor(private gameService: GameService, private gameLogService: GameLogService, private router: Router) {
    this.listOfGames = [];
    this.selectedGame = '';
    this.armyData = {name: '', strategy: 'RANDOM', units: 0};
  }

  ngOnInit() {
    this.listGames();
  }

  createGame(): void {
    this.subscription = this.gameService.createGame().subscribe();
  }

  listGames(): void {
    this.subscription = this.gameService.listGames().subscribe((list) => {
      this.listOfGames = Object.keys(list).map(key => ({ id: key, army: list[key] }));
    });
  }

  addArmy(gameId: string, armyData: ArmyData): void {
    if (this.selectedGame) {
      this.router.navigate(['/game/add-army'], { queryParams: { gameId: this.selectedGame } });

    }
    this.subscription = this.gameService.addArmy(gameId, armyData).subscribe();
    this.armyData = { name: '', strategy: 'RANDOM', units: 0 };
  }

  runAttack(gameId: string): void {
    this.subscription = this.gameService.runAttack(gameId).subscribe();
  }

  reset(gameId: string): void {
    this.subscription = this.gameService.reset(gameId).subscribe();
  }

  listGameLog(gameId: string): void {
    this.subscription = this.gameLogService.getLogs(gameId).subscribe();
  }

  onGameSelected(): void {
    if (this.selectedGame) {
      this.router.navigate(['/game'], { queryParams: { gameId: this.selectedGame } });
    }
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}