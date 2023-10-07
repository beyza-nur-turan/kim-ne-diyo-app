import {Layout, Menu, theme, Carousel, Card} from 'antd';
const {Meta} = Card;
import {useEffect, useState} from 'react';
import Avatar from '@mui/material/Avatar';
import { deepOrange, deepPurple } from '@mui/material/colors';
import Stack from '@mui/material/Stack';
import {AppstoreOutlined, HomeOutlined, UserOutlined} from "@ant-design/icons";
const {Header, Content, Footer} = Layout;
const HomePage = () => {
    const [data, setData] = useState([]);
    const [isLoading, setIsloading] = useState(false);
    const {
        token: {colorBgContainer},
    } = theme.useToken();
    const fetchApi = async () => {
        setIsloading(true);
        const data = await fetch("https://v6.exchangerate-api.com/v6/10c208cb5da6da73562b17b4/latest/USD")
            .then((res) => res.json())
            .then((json) => {
                return json;
            });
        setData(data);
        setIsloading(false);
        console.log("data: ", data);
    };
    useEffect(() => {
        fetchApi();
    }, []);
    return (
        <Layout className="layout">
            <Header className="header" >
                <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']}>
                    <Menu.Item key="1" icon={<HomeOutlined />}>
                        Home
                    </Menu.Item>
                    <Menu.Item key="2" icon={<UserOutlined />}>
                        Profile
                    </Menu.Item>
                    <Menu.Item key="3" icon={<AppstoreOutlined />}>
                        Products
                    </Menu.Item>
                    <Menu.Item key="4" icon={<AppstoreOutlined />}>
                        About
                    </Menu.Item>
                    <Menu.Item key="5">
                    <Stack direction="row" spacing={2}>
                        <Avatar sx={{ bgcolor: deepPurple[500] }}>BT</Avatar>
                    </Stack>
                    </Menu.Item>
                </Menu>

            </Header>

            <Content
                style={{
                    padding: '0 50px',
                }}
            >
                <div
                    className="site-layout-content"
                    style={{
                        background: colorBgContainer,
                    }}
                >
                    <div className="carouselStyle">
                        <Carousel autoplay>
                            <div >
                                <Card
                                    hoverable
                                    style={{
                                        width: 240,
                                    }}
                                    cover={<img alt="example" src="src/assets/dolar.jpg"/>}
                                >
                                    <Meta title="Kuşların nesli tükeniyor mu?" description="www.instagram.com"/>
                                </Card>
                            </div>
                            <div>
                                <Card
                                    hoverable
                                    style={{
                                        width: 240,
                                    }}
                                    cover={<img alt="example" src="src/assets/x.jpg"/>}
                                >
                                    <Meta title="Hayvanların nesli tükeniyor mu?" description="www.instagram.com"/>
                                </Card>
                            </div>
                            <div>
                                <Card
                                    hoverable
                                    style={{
                                        width: 240,
                                    }}
                                    cover={<img alt="example" src="src/assets/white.jpg"/>}
                                >
                                    <Meta title="Canlıların nesli tükeniyor mu?" description="www.instagram.com"/>
                                </Card>
                            </div>

                        </Carousel></div>
                </div>
            </Content>
            <Footer
                style={{
                    textAlign: 'center',
                }}
            >
                Tüm hakları sigunda saklıdır.
            </Footer>
        </Layout>
    );
};
export default HomePage;


