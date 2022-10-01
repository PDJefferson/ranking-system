import {createSlice} from "@reduxjs/toolkit";

const initialState= {
    players: []

}

export const playerSlice = createSlice( {
    name: "players-data",
    initialState,
    reducers: {
        getPlayers: (state, action) => {
            state.players = action.payload.players;
        },
        resetData: (state) => void(state.players = []),
    }
})

export const playerActions = playerSlice.actions;


