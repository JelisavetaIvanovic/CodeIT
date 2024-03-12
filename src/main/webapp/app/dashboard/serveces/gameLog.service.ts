import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from '../../app.constants';

@Injectable({ providedIn: 'root' })
export class GameLogService {
    private _http = inject(HttpClient);

    public addLog(): Observable<string>{
        return this._http
            .post<string>(`${SERVER_API_URL}/game-log`, {})
    }

    public getLogs(gameId: string): Observable<string[]>{
        return this._http
            .get<string[]>(`${SERVER_API_URL}/game-log/get-logs?gameId=${gameId}`, {})
    }
}