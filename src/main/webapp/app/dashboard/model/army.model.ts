export interface Army{
    id: string;
    game: string; 
    name: string;
    strategy: Strategy;
    initialUnits: number;
    currentUnits: number;
    availableAttackDate: Date;
    attackId: number;
    preinitialized: boolean;
}

export interface ArmyData{
    name: string;
    strategy: Strategy;
    units: number;
}

export type Strategy = "RANDOM" | "WEAKEST" | "STRONGEST";