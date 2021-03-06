import React from 'react';
import '../App.css'
import '../css/home.css'
import '../css/BookDetails.css'
import {Button, Descriptions, Input, Layout, message, Upload} from 'antd';
import HeaderInfo from "../component/Header";
import {getBook, modifyBook, modifyBookstate} from "../services/bookService";
import "antd/dist/antd.css";
import {UploadOutlined} from '@ant-design/icons';
import SideBarAdmin from "../component/SideBarAdmin";

const {Footer} = Layout;
const {TextArea} = Input;

class BookDetailAdminView extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            bookInfo: null,
            edit: false,
            name: null,
            isbn: null,
            author: null,
            type: null,
            inventory: null,
            price: null,
            description: null,
            image: null,
        };
        this.handleAuthor = this.handleAuthor.bind(this);
        this.handleDescription = this.handleDescription.bind(this);
        this.handleInventory = this.handleInventory.bind(this);
        this.handleIsbn = this.handleIsbn.bind(this);
        this.handleName = this.handleName.bind(this);
        this.handlePrice = this.handlePrice.bind(this);
        this.handleType = this.handleType.bind(this);
        this.customRequest = this.customRequest.bind(this);
    }

    componentDidMount() {
        const callback = (data) => {
            this.setState({
                bookInfo: data,
                name: data.name,
                isbn: data.isbn,
                author: data.author,
                type: data.type,
                inventory: data.inventory,
                price: data.price,
                description: data.description,
                image: data.image,
            });
        };
        const query = this.props.location.search;
        const arr = query.split('&');
        const bookId = arr[0].substr(4);
        // getBook(bookId, (data) => {this.setState({bookInfo: data})})
        getBook(bookId, callback);
    }

    handleDelete() {
        modifyBookstate(this.state.bookInfo.bookId, 0);
        message.success("????????????");
    }

    handleEdit() {
        this.setState({edit: true});
    }

    cancelEdit() {
        this.setState({edit: false});
    }

    completeEdit() {
        const callback = (data) => {
            this.setState({
                edit: false,
                bookInfo: data,
                name: data.name,
                isbn: data.isbn,
                author: data.author,
                type: data.type,
                inventory: data.inventory,
                price: data.price,
                description: data.description,
                image: data.image,
            })
        };
        modifyBook(this.state.bookInfo.bookId, this.state.name, this.state.isbn,
            this.state.type, this.state.author, this.state.price, this.state.description, this.state.inventory, this.state.image, callback);
    }

    handleName(e) {
        this.setState({name: e.target.value});
    }

    handleAuthor(e) {
        this.setState({author: e.target.value});
    }

    handleType(e) {
        this.setState({type: e.target.value});
    }

    handlePrice(e) {
        this.setState({price: e.target.value})
    }

    handleInventory(e) {
        this.setState({inventory: e.target.value});
    }

    handleDescription(e) {
        this.setState({description: e.target.value});
    }

    handleIsbn(e) {
        this.setState({isbn: e.target.value});
    }

    customRequest(option) {
        const callback = (data) => {
            this.setState({image: data});
        };
        const formData = new FormData();
        formData.append("files[]", option.file);
        const reader = new FileReader();
        reader.readAsDataURL(option.file);
        reader.onloadend = function (e) {
            callback(e.target.result);
            // console.log(e.target.result);// ???????????????base64
            // if (e && e.target && e.target.result) {
            //     option.onSuccess();
            // }
        };
    }

    beforeUpload(file) {
        const isJpgOrPng = file.type === "image/jpeg" || file.type === "image/png";
        if (!isJpgOrPng) {
            message.error("????????????JPG???PNG??????!");
            return false;
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
            message.error("?????????????????????2MB!");
            return false;
        }
        return isJpgOrPng && isLt2M;
    }

    render() {
        if (this.state.bookInfo == null) {
            return (
                <div>
                    <p>404 error</p>
                </div>
            )
        }
        console.log(this.state.image);
        return (
            <Layout>
                <SideBarAdmin/>
                <Layout className="site-layout" style={{marginLeft: 200}}>
                    <HeaderInfo/>
                    <div className="bookList">
                        <div className={"content"}>
                            <div className={"book-detail"}>
                                <div className={"book-image"}><img alt="image" src={this.state.bookInfo.image}
                                                                   style={{width: "350px", height: "350px"}}/></div>
                                <div className={"descriptions"}>
                                    {this.state.edit ?
                                        (
                                            <div>
                                                <Input onBlur={this.handleIsbn}
                                                       prefix={<p>isbn:</p>}
                                                       defaultValue={this.state.isbn}
                                                />
                                                <Input onBlur={this.handleName}
                                                       prefix={<p>??????:</p>}
                                                       defaultValue={this.state.name}
                                                />
                                                <Input onBlur={this.handleAuthor}
                                                       prefix={<p>??????:</p>}
                                                       defaultValue={this.state.author}/>
                                                <Input onBlur={this.handleType}
                                                       prefix={<p>??????:</p>}
                                                       defaultValue={this.state.type}/>
                                                <Input onBlur={this.handlePrice}
                                                       prefix={<p>??????:</p>}
                                                       defaultValue={'??' + this.state.price}/>
                                                <Input onBlur={this.handleInventory}
                                                       prefix={<p>??????:</p>}
                                                       defaultValue={this.state.inventory}/>
                                                <TextArea onBlur={this.handleDescription}
                                                          prefix={<p>?????????</p>}
                                                          rows={10} defaultValue={this.state.description}/>
                                            </div>
                                        ) : (
                                            <Descriptions>
                                                <Descriptions.Item label={"isbn"}
                                                                   span={3}>{this.state.bookInfo.isbn}</Descriptions.Item>
                                                <Descriptions.Item className={"title"}
                                                                   span={3}>{this.state.bookInfo.name}</Descriptions.Item>
                                                <Descriptions.Item label={"??????"}
                                                                   span={3}>{this.state.bookInfo.author}</Descriptions.Item>
                                                <Descriptions.Item label={"??????"}
                                                                   span={3}>{this.state.bookInfo.type}</Descriptions.Item>
                                                <Descriptions.Item label={"??????"} span={3}>{<span
                                                    className={"price"}>{'??' + this.state.bookInfo.price}</span>}</Descriptions.Item>
                                                <Descriptions.Item label={"?????? "}
                                                                   span={3}>{this.state.bookInfo.inventory !== 0 ?
                                                    <span>?????? <span
                                                        className={"inventory"}>??????{this.state.bookInfo.inventory}???</span></span> :
                                                    <span className={"status"}>??????</span>}</Descriptions.Item>
                                                <Descriptions.Item label={"????????????"}
                                                                   span={3}>{this.state.bookInfo.description}</Descriptions.Item>
                                            </Descriptions>
                                        )}
                                </div>
                            </div>
                            <div className={"button-groups"}>
                                {this.state.edit ? (
                                    <div>
                                        <Button type="danger" size={"large"}
                                            // style={{marginLeft:"15%"}}
                                                ghost
                                                onClick={this.completeEdit.bind(this)}>
                                            ??????
                                        </Button>
                                        <Button type="danger" size={"large"}
                                                onClick={this.cancelEdit.bind(this)}>
                                            ??????
                                        </Button>
                                        <Upload {...{
                                            customRequest: this.customRequest,
                                            showUploadList: false, // ?????????????????????
                                            beforeUpload: this.beforeUpload
                                        }}>
                                            <Button>
                                                <UploadOutlined/> Click to Upload
                                            </Button>
                                        </Upload>
                                    </div>
                                ) : (
                                    <div>
                                        <Button type="danger" size={"large"}
                                                onClick={this.handleEdit.bind(this)}>
                                            ??????
                                        </Button>
                                        <Button type="danger" size={"large"}
                                                onClick={this.handleDelete.bind(this)}>
                                            ????????????
                                        </Button>
                                    </div>

                                )
                                }
                            </div>
                        </div>
                    </div>
                    <Footer style={{textAlign: 'center'}}>Copyright ??2020 Created by Mark</Footer>
                </Layout>
            </Layout>

        )
    }


}

export default BookDetailAdminView;