import {useCallback, useReducer} from "react";
import {useDispatch} from "react-redux";
import {httpActions} from "../store/reducers/http-reducer";

function httpReducer(state, action) {
    switch (action.type){
        case 'SUCCESS': {
            return {
                data: action.responseData,
            }
        }
        default: {
            return state;
        }
    }
}
export default function useHttp(requestFunctionOne,requestFunctionTwo = function(){}, startWithPending = false) {
    const [httpState, dispatch] = useReducer(httpReducer, {
        data: null
    })

    const sendRequest = useCallback(
        async function () {
            try {
                const responseData = await Promise.all([requestFunctionOne(), requestFunctionTwo()]);
                // dispatchR(httpActions.success({
                //     status: responseData?.status || 200,
                //     responseMessage: responseData?.statusText || "success"
                // }));
                dispatch({ type: 'SUCCESS', responseData});
            } catch (error) {
               // dispatchR(httpActions.error({
               //     status: error?.status || 404,
               //     responseMessage: error?.statusText || "Something went wrong"
               // }))
            }
        },
        [requestFunctionOne,requestFunctionTwo, dispatch]
    );

    return {
        sendRequest,
        ...httpState
    };
}
