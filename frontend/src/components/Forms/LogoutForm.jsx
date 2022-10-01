import {authActions} from "../../store/reducers/auth-reducer";
import {httpActions} from "../../store/reducers/http-reducer";
import {playerActions} from "../../store/reducers/players-reducer";

import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import {useState} from "react";

export default function LogoutForm() {
    const [showModal, setShowModal] = useState(true);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleNoAction = () => {
        navigate("/players", {replace: true});
        setShowModal(false);
    }

    const handleYesAction = () => {
        dispatch(authActions.resetData({}));
        dispatch(playerActions.resetData({}));
        dispatch(httpActions.resetHttpValues({}));
        setShowModal(false);
        navigate("/home", {replace: true});
    }
    return (
        <>
            <p>logout</p>
            {showModal ? (
                <>
                    <div
                        className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none"
                    >
                        <div className="relative w-auto my-6 mx-auto max-w-3xl">
                            {/*content*/}
                            <div
                                className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                                {/*header*/}
                                <div className="flex items-start justify-between p-5 border-b border-solid border-slate-200 rounded-t">
                                    <h3 className="text-3xl  text-sky-800 font-semibold">
                                        Close Session
                                    </h3>
                                    <button className="p-1 ml-auto bg-transparent border-0 text-black opacity-5 float-right text-3xl leading-none font-semibold outline-none focus:outline-none"
                                        onClick={() => setShowModal(false)}
                                    >
                                        <span className="bg-transparent text-black opacity-5 h-6 w-6 text-2xl block outline-none focus:outline-none">
                                          ×
                                        </span>
                                    </button>
                                </div>
                                {/*body*/}
                                <div className="relative p-6 flex-auto">
                                    <p className="my-4 text-slate-500 text-lg leading-relaxed">
                                        Do you want to close the session?
                                    </p>
                                </div>
                                {/*footer*/}
                                <div
                                    className="flex items-center justify-end p-6 border-t border-solid border-slate-200 rounded-b">
                                    <button
                                        className="text-red-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                                        type="button"
                                        onClick={handleNoAction}
                                    >
                                        No
                                    </button>
                                    <button
                                        className="bg-sky-800 text-white active:bg-emerald-600 font-bold uppercase text-sm px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                                        type="button"
                                        onClick={handleYesAction}
                                    >
                                        Yes
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
                </>
            ) : null}
        </>
    );

}