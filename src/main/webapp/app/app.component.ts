import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { GameService } from './dashboard/serveces/game.service';
import { GameList } from './dashboard/model/game.model';
import { ArmyData } from './dashboard/model/army.model';
import { GameLogService } from './dashboard/serveces/gameLog.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit{
  gameId = "44695632-0ef5-4d50-86fe-024391cf4993";
  armyData: ArmyData = { name: 'Laki', strategy: 'RANDOM', units: 100 };
  constructor(private gameService: GameService, private gameLogService: GameLogService) {}

  title = 'codeIT';

  ngOnInit(): void {
    this.listGameLog(this.gameId);
  }

  createGame(): void {
    this.gameService.createGame().subscribe(
      (response: string) => {
        console.log('Game created successfully with ID:', response);
      },
      (error: string) => {
        console.error('Error creating game:', error);
      }
    );
  }

  listGames(): void {
    this.gameService.listGames().subscribe(
      (response: GameList) => {
        console.log('Game created successfully with ID:', response);
      },
      (error: GameList) => {
        console.error('Error creating game:', error);
      }
    );
  }

  addArmy(gameId: string, armyData: ArmyData): void {
    this.gameService.addArmy(gameId, armyData).subscribe(
      () => {
        console.log('Army added successfully');
      },
      (error) => {
        console.error('Error adding army:', error);
      }
    );
  }

  runAttack(gameId: string): void {
    this.gameService.runAttack(gameId).subscribe(
      () => {
        console.log('Army added successfully');
      },
      (error) => {
        console.error('Error adding army:', error);
      }
    );
  }

  reset(gameId: string): void {
    this.gameService.reset(gameId).subscribe(
      () => {
        console.log('Army added successfully');
      },
      (error) => {
        console.error('Error adding army:', error);
      }
    );
  }

  listGameLog(gameId: string): void {
    this.gameLogService.getLogs(gameId).subscribe(
      () => {
        console.log('Army added successfully');
      },
      (error) => {
        console.error('Error adding army:', error);
      }
    );
  }

  
}
