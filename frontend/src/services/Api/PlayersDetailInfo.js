import {createListenerMiddleware} from "@reduxjs/toolkit";
import {API_ENUM} from "../../scripts/api-endpoints";
import {playerActions} from "../../store/reducers/players-reducer";
import {httpActions} from "../../store/reducers/http-reducer";


const userMiddleware = createListenerMiddleware();

userMiddleware.startListening({
    type : "GET_PLAYERS_DETAIL_INFO",
    effect: async (action, listenerApi) => {
        const fetchData = async () => {
            const response = await fetch(API_ENUM.API_ENDPOINT + API_ENUM.GET_USERS,
                {
                    method: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + action.payload
                    }
                });
            if (!response.ok) {
                listenerApi.dispatch(httpActions.error({
                    responseMessage: response.statusText,
                    status: response.status

                }))
                throw new Error(response.statusText);
            }
            listenerApi.dispatch(httpActions.success({
                responseMessage: response.statusText,
                status: response.status

            }))
            return await response.json();
        }
        try {
            const players = await fetchData();
            listenerApi.dispatch(playerActions.getPlayers({
                     players: players?.players || []
                }
            ));
        } catch (error) {
            console.log(error);
        }
    },
});

export default userMiddleware;

