
const User = (props) => {

    return (
        <div>
            <ul>
                <li>{props.nickname}</li>
                <li>{props.rank}</li>
                <li>{props.wins}</li>
            </ul>
        </div>
    )
}

export default User;