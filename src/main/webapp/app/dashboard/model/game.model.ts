export interface Game {
    id: string ;
    status: Status ;
    creationDate: Date;
    numberOfArmies: number;
    lastAttackId: number;
}
export type Status = 'NEW' | 'ACTIVE' | 'ENDED';
export type GameList = Record<string, string[]>
