import { useContext, useEffect } from "react";
import { useState } from "react";
import { createContext } from "react";
const userContext = createContext();

const Provider = ({ children }) => {
  const [userList, setUserList] = useState([]);
  useEffect(() => {
    //sayfanın güncellenmesi ya da yeniden yüklenmesi durumunda
    //verilerin localstoragede yüklü kalmasını sağlar
    const lastUser = JSON.parse(localStorage.getItem("userList"));
    if (lastUser) {
      setUserList(lastUser);
    }
  }, []);

  useEffect(() => {
    //console.log("güncel kullanıcı listesi", userList);
  }, [userList]);
  const addUser = (newUser) => {
    const id = Math.random().toString(36).substring(7);
    setUserList([...userList, { ...newUser, id }]);
    localStorage.setItem("userList", JSON.stringify(userList));
  };
  const data = { userList, setUserList, addUser };
  return <userContext.Provider value={data}>{children}</userContext.Provider>;
};
export const useAuth = () => useContext(userContext);
export default Provider;
