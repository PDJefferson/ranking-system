import {NavLink} from "react-router-dom";

export default function List(props) {

    return (

        <div className="grid grid-cols-3 gap-6 mb-4 border-b-2 border-slate-700">
            <div >
                <h1>{props.id+1 + ")." }  Name: { props.name}</h1>
            </div>
            <div>
                <h2>Power: { props.power}</h2>
            </div>
            <div>
                <h2>Alliance: {" "}
                    <NavLink to={`/alliance/${props.allianceTag}`}>
                        {props.allianceTag}
                    </NavLink>
                </h2>
            </div>
        </div>
    )
}