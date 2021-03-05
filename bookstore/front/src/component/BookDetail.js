import React from 'react';
import {Button, Descriptions, message} from 'antd';
import "../css/BookDetails.css"

export class BookDetail extends React.Component {
    toggleAddToCart(id, name, price) {
        //console.log("bookid:"+id.toString());
        if (localStorage.getItem(id.toString()) == null) {
            let information = name + "&" + price.toString() + "&1";
            localStorage.setItem(id.toString(), information);
        } else {
            const arr = localStorage.getItem(id.toString()).split("&");
            let num = parseInt(arr[2]) + 1;
            const newInformation = arr[0] + "&" + arr[1] + "&" + num.toString();
            localStorage.setItem(id.toString(), newInformation);
        }
        // for (let i = 0; i < localStorage.length; i++) {
        //     let key = localStorage.key(i);
        //     console.log(key);
        //     console.log(localStorage.getItem(key));
        //     console.log("---------------------------------");
        // }

        message.success("the book is added to your cart")

    }

    render() {
        const {info} = this.props;
        if (info == null) {
            return (
                <div>
                    <p>404 error</p>
                </div>
            )
        }

        return (
            <div className={"content"}>
                <div className={"book-detail"}>
                    <div className={"book-image"}><img alt="image" src={info.image}
                                                       style={{width: "350px", height: "350px"}}/></div>
                    <div className={"descriptions"}>
                        <Descriptions>
                            <Descriptions.Item className={"title"} span={3}>{info.name}</Descriptions.Item>
                            <Descriptions.Item label={"作者"} span={3}>{info.author}</Descriptions.Item>
                            <Descriptions.Item label={"分类"} span={3}>{info.type}</Descriptions.Item>
                            <Descriptions.Item label={"定价"} span={3}>{<span
                                className={"price"}>{'¥' + info.price}</span>}</Descriptions.Item>
                            <Descriptions.Item label={"状态 "} span={3}>{info.inventory !== 0 ?
                                <span>有货 <span className={"inventory"}>库存{info.inventory}件</span></span> :
                                <span className={"status"}>无货</span>}</Descriptions.Item>
                            <Descriptions.Item label={"作品简介"} span={3}>{info.description}</Descriptions.Item>
                        </Descriptions>
                    </div>
                </div>
                <div className={"button-groups"}>
                    <Button type="danger" size={"large"}
                            onClick={() => this.toggleAddToCart(info.bookId, info.name, info.price)}>
                        加入购物车
                    </Button>

                    <Button type="danger" size={"large"} style={{marginLeft: "15%"}} ghost>
                        立即购买
                    </Button>
                </div>
            </div>


        )

    }

}