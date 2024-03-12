import { createFeature, createReducer, on } from '@ngrx/store';
import { Game } from '../dashboard/model/game.model';
import { Army } from '../dashboard/model/army.model';

export interface State {
    game: Game;
    army: Army;
}