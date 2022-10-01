import {createSlice} from '@reduxjs/toolkit';

const initialState = {
    authorized: false,
    jwt: '',
}

export const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        setJwt: (state, action) => {
            state.jwt = action.payload;
        },
        setAuthorized: (state) => {
            state.authorized = true;
        },
        resetData: (state) => {
            state.authorized = false;
            state.jwt = '';
        }
    }
});

export const authActions = authSlice.actions;




