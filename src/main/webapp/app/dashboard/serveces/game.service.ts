import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from '../../app.constants';
import { GameList } from '../model/game.model';
import { ArmyData } from '../model/army.model';

@Injectable({ providedIn: 'root' })
export class GameService {
    private _http = inject(HttpClient);

    public createGame(): Observable<string>{
        return this._http
            .post<string>(`${SERVER_API_URL}/game/create-game`, {})
    }

    public listGames(): Observable<GameList>{
        return this._http
            .get<GameList>(`${SERVER_API_URL}/game/list-games`, {})
    }
    
    public addArmy(gameId: string, army: ArmyData): Observable<void>{
        return this._http.post<void>(`${SERVER_API_URL}/game/add-army?gameId=${gameId}`, { army });
    }

    public runAttack(gameId: string): Observable<void>{
        return this._http
            .post<void>(`${SERVER_API_URL}/game/run-attack?gameId=${gameId}`, {});
    }

    public reset(gameId: string): Observable<void>{
        return this._http
            .delete<void>(`${SERVER_API_URL}/game/reset?gameId=${gameId}`, {});
    }
}
