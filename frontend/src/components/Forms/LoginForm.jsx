import {LockClosedIcon} from '@heroicons/react/solid'
import {useDispatch, useSelector} from 'react-redux';
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {httpActions} from "../../store/reducers/http-reducer";
import {authActions} from "../../store/reducers/auth-reducer";
import AlertMessage from "../Alerts/AlertMessage";
import Spinner from "../ui/Spinner";

export default function LoginForm() {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const {error, status} = useSelector(state => state.http);
    const [isSubmitted, setIsSubmitted] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [hasError, setHasError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    useEffect(() => {
        console.count('check ' + status)
        if (isSubmitted) {
            if(username !== '' && password !== '') {
                dispatch({type: "POST_LOGIN", payload: {username, password}});
            } else if (status === 200) {
                dispatch(authActions.setAuthorized({}));
                setIsSubmitted(false);
                dispatch(httpActions.resetHttpValues({}));
                navigate("/players", {replace: true});
            } else if (status === 401) {
                setHasError(true);
                setIsSubmitted(false);
                setErrorMessage('Invalid Credentials');
                dispatch(httpActions.resetHttpValues({}));
            }else if(status === 0){
            }else {
                console.log(error);
                dispatch(httpActions.resetHttpValues({}));
                setIsSubmitted(false);
                setHasError(true);
                setErrorMessage('An Error Has Occurred');
            }
            setUsername('');
            setPassword('');
        }
    }, [username, password, navigate, status, error, dispatch, isSubmitted]);

    const handleSubmit = (event) => {
        event.preventDefault();
        setUsername(event.target.username.value);
        setPassword(event.target.password.value);
        setIsSubmitted(true);
    }

    const usernameChangeHandler = (event) => {
        setHasError(false);
        setUsername(event.target.value);
    }

    const passwordChangeHandler = (event) => {
        setHasError(false);
        setPassword(event.target.value);
    }

    if(status === 0 && isSubmitted){
        return( <Spinner/> );
    }
    return (
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
                    {hasError && <AlertMessage message={errorMessage} code={'bg-rose-400'}/>}
                </div>
            </div>

            <div className="flex items-center justify-between">
                <div className="flex items-center">
                    <input
                        id="remember-me"
                        name="remember-me"
                        type="checkbox"
                        className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                    />
                    <label htmlFor="remember-me" className="ml-2 block text-sm text-gray-900">
                        Remember me
                    </label>
                </div>
                <div className="text-sm">
                    <a href="#" className="font-medium text-indigo-600 hover:text-indigo-500">
                        Forgot your password?
                    </a>
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
                  <LockClosedIcon className="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" aria-hidden="true"/>
                </span>
                    Sign in
                </button>
            </div>
        </form>
    );

}