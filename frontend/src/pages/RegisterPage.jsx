import {NavLink} from "react-router-dom";
import RegisterForm from "../components/Forms/RegisterForm";

export default function RegisterPage() {

    return (
        <div className="min-h-full flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
            <div className="max-w-md w-full space-y-8">
                <div>
                    <h2 className="mt-6 text-center text-3xl tracking-tight font-bold text-gray-900">
                        Sign up to create an account
                    </h2>
                    <p className="mt-2 text-center text-sm text-gray-600">
                        Already have an account?{' '}
                        <NavLink className="font-medium text-indigo-600 hover:text-indigo-500" to="/login">
                            Login
                        </NavLink>
                    </p>
                </div>
                <RegisterForm/>
            </div>
        </div>
    )

}