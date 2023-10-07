// import React, { useState } from 'react';
// import { Carousel, Radio } from 'antd';
// const contentStyle = {
//     height: '100%',
//     color: '#fff',
//     lineHeight: '160px',
//     textAlign: 'center',
//     background: '#364d79',
// };
// const EmptyPage = () => {
//     const [dotPosition, setDotPosition] = useState('top');
//     const handlePositionChange = ({ target: { value } }) => {
//         setDotPosition(value);
//     };
//     return (
//         <>
//             <Radio.Group
//                 onChange={handlePositionChange}
//                 value={dotPosition}
//                 style={{
//                     marginBottom: 8,
//                 }}
//             >
//
//                 <Radio.Button value="right">Right</Radio.Button>
//             </Radio.Group>
//             <Carousel dotPosition={dotPosition}>
//                 <div>
//                     <h3 style={contentStyle}>1</h3>
//                 </div>
//                 <div>
//                     <h3 style={contentStyle}>2</h3>
//                 </div>
//                 <div>
//                     <h3 style={contentStyle}>3</h3>
//                 </div>
//                 <div>
//                     <h3 style={contentStyle}>4</h3>
//                 </div>
//             </Carousel>
//         </>
//     );
// };
// export default EmptyPage;




import { Form, Formik } from "formik";
import Input from "../components/form/Input";
import Swal from "sweetalert2";
import { Col, Row,Button } from 'antd';
import { useNavigate } from "react-router-dom";

export default function RegisterPage  ()  {

    const navigate = useNavigate();

    const submitted=()=>{
        Swal.fire(
            "Kayıt Başarılı!",
            "Kullanıcı başarılı bir şekilde kaydedildi." ,
            "success"
        )
    }
    const getLogin=()=>{
        navigate("/login")
    }

    const handleFormSubmit = async (value,resetForm) => {
        if (value.password !== value.confirmPassword) {
            alert('Passwords do not match!');
        }

        await  fetch('http://localhost:8080/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(value),
        })
            .then((response) => {
                console.log("response:",response)
                if (response.ok) {
                    // Registration successful
                    response.json().then((data) => {
                        if (data.resultCode === "Success") {
                            console.log('Registration successful');
                        } else {
                            console.log("Gelen Mesaj:", data);
                            throw new Error(data.message);
                        }
                    });
                    //submitted()
                } else {
                    // Handle registration error
                    throw new Error('Registration failed');
                }
            })
            .catch((error) => {
                // Handle error
                console.error(error);
            });
        resetForm();
        // Reset the form after successful registration

    };

    return (
        <Row className="rowStyle">
            <Col span={12}>{/*<div><h1>KİM NE DİYO</h1></div> */}
                <img src="src/assets/logo1.gif"/></Col>
            <Col span={12}><div className="formStyle">
                <Formik
                    initialValues={{
                        name:"",
                        userName:"",
                        userSurname:"",
                        userMail:"",
                        userPhone:"",
                        password:"",
                        confirmPassword:"",
                    }}
                    onSubmit={(value,{ resetForm }) => {
                        handleFormSubmit(value, resetForm )

                    }}
                >
                    {() => (<div >
                            <h2 className="titleStyle">KAYIT EKRANI</h2>
                            <Form >
                                <Input  type="text" label="Kullanıcı adı giriniz:" name="name" />
                                <Input type="text" label="İsminizi giriniz:" name="userName" />
                                <Input type="text" label="Soyisminizi giriniz:" name="userSurname" />
                                <Input type="text" label="Mail adresinizi giriniz:" name="userMail" />
                                <Input type="text" label="Telefon numaranızı giriniz:" name="userPhone" />
                                <Input type="text" label="Şifrenizi giriniz:" name="password" />
                                <Input type="text" label="Şifreyi tekrar girin:" name={"confirmPassword"}/>
                                <button type="submit">KAYDOL</button>
                            </Form>
                        </div>
                    )}
                </Formik>
                <div className="buttonStyle"><div>Daha önce kayıt yaptırdınız mı?</div><Button type="text" onClick={getLogin}>Giriş Yap</Button></div>


            </div></Col>
        </Row>

    );


}

