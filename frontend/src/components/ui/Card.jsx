import {NavLink} from "react-router-dom";
import List from "./List";

export default function CustomCard(props) {
    console.log(props.object);
    return (
        <div className="flex justify-center">
            <div className="rounded-lg shadow-lg bg-white max-w-lg">
                <div className="p-6">
                    <NavLink to={"/playerList"} className="text-gray-900 text-xl font-bold mb-2 hover:text-blue-400">
                        <p>
                            Top {props.title} <span className="text-gray-200">  > </span>
                        </p>
                    </NavLink>
                </div>
                <div className="p-6">
                    {props.object.map((player, index) =>
                        <List key={index} id={index} name={player?.name} power={player?.power}
                              allianceTag={player?.allianceTag}></List>
                    )}
                </div>
            </div>
        </div>
    );
}