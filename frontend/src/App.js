import './App.css';
import {Routes, Route, useNavigate, Navigate} from 'react-router-dom';
import Layout from './components/layout/Layout';
import UserPage from './pages/UserPage';
import {useSelector} from "react-redux";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import Home from './pages/Home';

function App() {
    const authorized = useSelector(state => state.auth.authorized);
    return (
        <Layout>
            <Routes>
                <Route path="/" element={<Navigate replace to="/home" />} />
                <Route path="/home" element={<Home />}></Route>
                <Route path="/players" element={authorized ? <UserPage/> : <Navigate to={"/login"}/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                <Route path="/register" element={<RegisterPage/>} />
            </Routes>
        </Layout>
    );
}

export default App;