import {NavLink} from "react-router-dom";
import {useState} from "react";
import {Disclosure} from '@headlessui/react'
import {MenuIcon, XIcon} from '@heroicons/react/outline'
import LogoutForm from "../Forms/LogoutForm";
import {useSelector} from "react-redux";
import SearchBar from "./SearchBar";

const navigation = [
    {name: 'Start', to: '/', current: true},
    {name: 'About', to: '/', current: false }
]

function classNames(...classes) {
    return classes.filter(Boolean).join(' ')
}

const MainNavigation = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isLoggedOut, setIsLogout] = useState(false);
    const authorized = useSelector(state => state.auth.authorized);
    const loginHandler = () => {
        setIsLoggedIn(!isLoggedIn);
        setIsLogout(false);
    }

    const logoutHandler = () => {
        setIsLogout(!isLoggedOut);
        setIsLoggedIn(false);
    }
    return (
        <Disclosure as="nav" className="bg-gray-800">
            {({open}) => (
                <>
                    <div className="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8">
                        <div className="relative flex items-center justify-between h-16">
                            <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
                                {/* Mobile menu button*/}
                                <Disclosure.Button
                                    className="inline-flex items-center justify-center p-2 rounded-md text-gray-400
                                    hover:text-white hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-inset
                                    focus:ring-white">
                                    <span className="sr-only">Open main menu</span>
                                    {open ? (
                                        <XIcon className="block h-6 w-6" aria-hidden="true"/>
                                    ) : (
                                        <MenuIcon className="block h-6 w-6" aria-hidden="true"/>
                                    )}
                                </Disclosure.Button>
                            </div>

                            <div className="flex-1 flex inset-y-0 items-center justify-center sm:items-stretch sm:justify-start">

                                <div className="hidden sm:block sm:ml-6">
                                    <div className="flex space-x-4 my-3">
                                        {navigation.map((item, index) => (
                                            <NavLink
                                                key={index}
                                                to={item.to}
                                                className={classNames(
                                                    item.current ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                                                    'px-3 py-2 rounded-md text-sm font-medium'
                                                )}
                                                aria-current={item.current ? 'page' : undefined}
                                            >
                                                {item.name}
                                            </NavLink>
                                        ))}

                                    </div>
                                </div>
                                <div className="flex mx-16 items-center">
                                    <SearchBar/>
                                </div>
                            </div>

                            <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">

                                {!authorized ? <NavLink className={classNames(isLoggedIn ?
                                            'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                                        'px-3 py-2 rounded-md text-sm font-medium')}
                                                        onClick={loginHandler}
                                                        to={!isLoggedIn ? "/login" : "/home"}> login </NavLink> :
                                    <div className={classNames(isLoggedOut ?
                                            'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                                        'px-3 py-2 rounded-md text-sm font-medium')}
                                         onClick={logoutHandler}> {isLoggedOut ? <LogoutForm/> : 'logout'}</div>
                                }
                            </div>


                        </div>

                    </div>

                    <Disclosure.Panel className="sm:hidden">
                        <div className="px-2 pt-2 pb-3 space-y-1">
                            {navigation.map((item) => (
                                <Disclosure.Button
                                    key={item.name}
                                    as="a"
                                    href={item.href}
                                    className={classNames(
                                        item.current ? 'bg-gray-900 text-white' : 'text-gray-300 ' +
                                            'hover:bg-gray-700 hover:text-white',
                                        'block px-3 py-2 rounded-md text-base font-medium'
                                    )}
                                    aria-current={item.current ? 'page' : undefined}
                                >
                                    {item.name}
                                </Disclosure.Button>
                            ))}
                        </div>
                    </Disclosure.Panel>
                </>
            )}
        </Disclosure>
        // <header className="w-full h-16 bg-blue-500 drop-shadow-lg">
        //     <div className="container px-6 md:px-0 h-full mx-auto flex justify-between items-center">
        //         Players Data
        //         <ul className="hidden fixed top-0 right-0 px-10 py-16 bg-gray-800 z-50
        //         md:relative md:flex md:p-0 md:bg-transparent md:flex-row md:space-x-6">
        //             <li className="md:hidden z-90 fixed top-4 right-6">
        //                 <a href={empty_space} className="text-right text-white text-4xl"
        //                    onClick={toggleMenu}>&times;</a>
        //             </li>
        //             <li>
        //                 <NavLink to='/players'
        //                          className="text-white opacity-70 hover:opacity-100 duration-300">
        //                     player data
        //                 </NavLink>
        //             </li>
        //             <li>
        //                 <NavLink to="/"
        //                          className="text-white opacity-70 hover:opacity-100 duration-300">
        //                     Add a Quote
        //                 </NavLink>
        //             </li>
        //         </ul>
        //         <div className="flex items-center md:hidden">
        //             <button
        //                 className={isActive ? "text-white text-4xl font-bold opacity-70 hover:opacity-100 " +
        //                     "duration-300 hidden w-full h-screen" : "text-white text-4xl font-bold opacity-70 " +
        //                     "hover:opacity-100 duration-300"}
        //                 onClick={toggleMenu}>
        //                 &#9776;
        //             </button>
        //         </div>
        //     </div>
        //
        // </header>
    );
};

export default MainNavigation;
