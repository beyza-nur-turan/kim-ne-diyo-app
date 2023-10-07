import { Route,Routes } from "react-router-dom";
import HomePage from "../pages/HomePage"
import RegisterPage from "../pages/RegisterPage";
import LoginPage from "../pages/LoginPage";
import FetchPage from "../components/Fetch";
import EmptyPage from "../pages/EmptyPage";
export default  function AppRouteList() {
  return (
    <>
    <Routes>
        <Route path="/" element={<HomePage/>}/>
        <Route path="/register" element={<RegisterPage/>}/>
        <Route path="/login" element={<LoginPage/>}/>
        <Route path="/fetch" element={<FetchPage/>}/>
        <Route path="/empty" element={<EmptyPage/>}/>
    </Routes>
    </>
  )
}


