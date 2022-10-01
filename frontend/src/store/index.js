import {configureStore} from '@reduxjs/toolkit';

import {rootReducer} from "./reducers";
import userMiddleware from "../services/Api/PlayersDetailInfo";
import loginMiddleware from "../services/Api/Auth";

const store = configureStore({
    reducer: rootReducer,
    middleware: getDefaultMiddleware => getDefaultMiddleware()
        .prepend(userMiddleware.middleware)
        .prepend(loginMiddleware.middleware),
    devTools: true
});

export default store;