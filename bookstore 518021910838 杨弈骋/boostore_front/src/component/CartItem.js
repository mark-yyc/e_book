import React from 'react';
import "../css/Cart.css"

export class CartItem extends React.Component {


    render() {

        const {info} = this.props;

        return (
            <div>
                <img alt="image" src={info.image} className={"itemImg"}/>
                <div className={"itemInfo"}>
                    <p>数量：{info.amount}</p>
                </div>
                <div className={"itemInfo"}>
                    <p style={{color: 'red'}}>¥ {info.price}</p>
                </div>
                <div className={"itemInfo"}>
                    <p>{info.name}</p>
                </div>

                {/*<p className={"itemInfo"}>{info.price}</p>*/}
                {/*<p className={"itemInfo"}>{info.amount}</p>*/}

            </div>
        );
    }

}