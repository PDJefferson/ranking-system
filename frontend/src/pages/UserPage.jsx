import {useSelector, useDispatch,} from "react-redux";
import Users from "../components/User/Users";
import {useEffect} from "react";

export default function UserPage() {
    const dispatch = useDispatch();
    const {jwt, authorized} = useSelector(state => state.auth);
    const users = useSelector(state => state.players.players);

    useEffect(() => {
        if(authorized && users.length <= 0 && jwt !== '') {
            console.log("check " + jwt);
            dispatch({type: "GET_PLAYERS_DETAIL_INFO", payload: jwt});
        }
    }, [dispatch, jwt, users, authorized]);

    return (
        <Users users={users}/>
    );
}
