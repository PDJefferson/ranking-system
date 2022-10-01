import { authSlice } from './auth-reducer';
import { httpSlice} from "./http-reducer";
import {playerSlice} from "./players-reducer";

export const rootReducer = {
    auth: authSlice.reducer,
    http: httpSlice.reducer,
    players: playerSlice.reducer
}
