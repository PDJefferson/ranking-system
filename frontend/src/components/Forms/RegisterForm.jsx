import {useEffect, useState} from "react";
import AlertMessage from "../Alerts/AlertMessage";
import {useDispatch, useSelector} from "react-redux";
import {useNavigate} from "react-router-dom";
import {httpActions} from "../../store/reducers/http-reducer";
import Spinner from "../ui/Spinner";

export default function RegisterForm() {
    const status = useSelector(state => state.http.status);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [coPassword, setCoPassword] = useState('');
    const [formSubmitted, setFormSubmitted] = useState(false);
    const [hasError, setHasError] = useState(false);
    const [errorType, setErrorType] = useState('');
    const navigate = useNavigate();
    const dispatch = useDispatch();
    useEffect(() => {
        if (formSubmitted) {
            if (coPassword !== password) {
                setErrorType("Passwords don't match!");
                setHasError(true);
                setFormSubmitted(false);
                setUsername('');
                setPassword('');
                setCoPassword('');
            } else {
                console.log("check");
                if (status === 0 && username !== '' && password !== '') {
                    dispatch({type: "REGISTER_USER", payload: {username, password}});
                    setUsername('');
                    setPassword('');
                    setCoPassword('');
                } else if (status === 409) {
                    setErrorType("Username exists!");
                    setHasError(true);
                    setFormSubmitted(false);
                    setUsername('');
                    setPassword('');
                    setCoPassword('');
                    dispatch(httpActions.resetHttpValues({}));
                } else if (status === 201) {
                    setFormSubmitted(false);
                    setUsername('');
                    setPassword('');
                    setCoPassword('');
                    dispatch(httpActions.resetHttpValues({}));
                    navigate("/login", {replace: true});
                }
            }
        }
    }, [username, formSubmitted, coPassword, password, dispatch, status, navigate]);

    const handleSubmit = (e) => {
        e.preventDefault();
        setUsername(e.target.username.value);
        setPassword(e.target.password.value);
        setCoPassword(e.target.coPassword.value);
        setFormSubmitted(true);
    }

    const usernameChangeHandler = (e) => {
        setUsername(e.target.value);
        setHasError(false);
    }

    const passwordChangeHandler = (e) => {
        setPassword(e.target.value);
        setHasError(false);
    }

    const conPasswordChangeHandler = (e) => {
        setCoPassword(e.target.value);
    }
    if (status === 0 && formSubmitted) {
        return (<Spinner/>);
    }

    return (
        <>
            <form className="mt-8 space-y-6" action="#" onSubmit={handleSubmit}>
                <input type="hidden" name="remember" defaultValue="true"/>
                <div className="rounded-md shadow-sm -space-y-px">
                    <div>
                        <label htmlFor="username" className="sr-only">
                            Username
                        </label>
                        <input
                            id="username"
                            name="username"
                            autoComplete="username"
                            type="text"
                            onChange={usernameChangeHandler}
                            value={username}
                            required
                            className="appearance-none rounded-none relative block w-full px-3 py-2 border
                                    border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none
                                    focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
                            placeholder="Username"
                        />
                    </div>
                    <div>
                        <label htmlFor="password" className="sr-only">
                            Password
                        </label>
                        <input
                            id="password"
                            name="password"
                            type="password"
                            onChange={passwordChangeHandler}
                            value={password}
                            autoComplete="current-password"
                            required
                            className="appearance-none rounded-none relative block w-full px-3 py-2 border
                                    border-gray-300 placeholder-gray-500 text-gray-900
                                    rounded-b-md focus:outline-none focus:ring-indigo-500
                                    focus:border-indigo-500 focus:z-10 sm:text-sm"
                            placeholder="Password"
                        />
                    </div>
                    <div>
                        <label htmlFor="password" className="sr-only">
                            Confirm Password
                        </label>
                        <input
                            id="coPassword"
                            name="coPassword"
                            type="password"
                            onChange={conPasswordChangeHandler}
                            value={coPassword}
                            autoComplete="current-password"
                            required
                            className="appearance-none rounded-none relative block w-full px-3 py-2 border
                                    border-gray-300 placeholder-gray-500 text-gray-900
                                    rounded-b-md focus:outline-none focus:ring-indigo-500
                                    focus:border-indigo-500 focus:z-10 sm:text-sm"
                            placeholder="Confirm Password"
                        />
                    </div>
                    <div className="space-y-3.5">
                        {hasError && <AlertMessage message={errorType} code={"bg-rose-400"}/>}
                    </div>
                </div>


                <div>
                    <button
                        type="submit"
                        className="group relative w-full flex justify-center py-2 px-4 border
                                border-transparent text-sm font-medium rounded-md text-white
                                bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2
                                focus:ring-offset-2 focus:ring-indigo-500"
                    >
                <span className="absolute left-0 inset-y-0 flex items-center pl-3">
                </span>
                        Create Account
                    </button>
                </div>

            </form>
        </>


    )

}