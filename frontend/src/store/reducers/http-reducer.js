import {createSlice} from '@reduxjs/toolkit';

const initialState = {
    error: null,
    status: 0
};

export const httpSlice = createSlice({
        name: 'http-error-handling',
        initialState,
        reducers: {
            send: (state) => state.status = 'pending',
            success:(state, action) => {
                state.status = action.payload.status;
            },
            error: (state, action) => {
                state.error = action.payload.responseMessage;
                state.status = action.payload.status;
            },
            unauthorized: ( state, action) => {
                state.error = action.payload.responseMessage;
                state.status = action.payload.status;
            },
            setPendingStatus: (state, action) => state.status = action.payload,
            resetHttpValues: (state) => {
                state.error = null;
                state.status = 0;
            }
        }
    }
);

export const httpActions = httpSlice.actions;