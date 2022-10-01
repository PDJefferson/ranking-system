import {useSelector} from "react-redux";
import User from './User';


const Users = (props) => {
    return (
        <div>
            {
                props.users.map((user, index) => (
                    <User key={index}
                          nickname={user.name}
                          wins={user.power}
                          rank={user.alliance}/>))
            }
        </div>
    )
}

export default Users;
