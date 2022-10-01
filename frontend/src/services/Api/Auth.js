import {createListenerMiddleware} from "@reduxjs/toolkit";
import {API_ENUM} from "../../scripts/api-endpoints";
import {authActions} from "../../store/reducers/auth-reducer";
import {httpActions} from "../../store/reducers/http-reducer";

const loginMiddleware = createListenerMiddleware();

loginMiddleware.startListening({
    type : "POST_LOGIN",
    effect: async (action, listenerApi) => {
        const fetchData = async () => {
            const response = await fetch(API_ENUM.API_ENDPOINT + API_ENUM.LOGIN,
                {
                    method: 'POST',
                    headers: new Headers({ "content-type": "application/json" }),
                    body: JSON.stringify(action.payload)
                });
            if (!response.ok) {
                listenerApi.dispatch(httpActions.error({
                    status: response.status,
                    responseMessage: response.statusText
                }));
                throw new Error(response.statusText);
            }
            listenerApi.dispatch(httpActions.success({
                status: response.status,
                responseMessage: response.statusText
            }));
            return await response.json();
        }
        try {
            const response = await fetchData();
            listenerApi.dispatch(authActions.setJwt(response.jwt));
        } catch (error) {
            console.log(error);
        }
    }
});

loginMiddleware.startListening({
    type: "REGISTER_USER",
    effect: async (action, listenerApi) => {
        if(action.payload.username.value === '' && action.payload.password.value === '') {
            throw new Error("no data");
        }
        const response = await fetch(API_ENUM.API_ENDPOINT + API_ENUM.REGISTER,
            {
                method: 'POST',
                headers: new Headers({"content-type" : "application/json"}),
                body: JSON.stringify(action.payload)
            })

        if (!response.ok){
            listenerApi.dispatch(httpActions.error({
                status: response.status,
                responseMessage: response.statusText
            }));
            throw new Error(response.statusText);
        }

        listenerApi.dispatch(httpActions.success({
            status: response.status,
            responseMessage: response.statusText
        }));
    }
})

export default loginMiddleware;