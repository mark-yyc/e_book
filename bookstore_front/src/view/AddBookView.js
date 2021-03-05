import React from 'react';
import '../App.css'
import '../css/home.css'
import {Button, Input, message, Result, Upload} from 'antd';
import {UploadOutlined} from '@ant-design/icons';
import {Link} from 'react-router-dom';
import {addBook} from "../services/bookService";

const {TextArea} = Input;

export class AddBookView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            submit: false,
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

    handleSubmit() {
        const callback = () => {
            this.setState({submit: true});
        }
        // if(this.state.name!=null&&this.state.isbn!=null&&this.state.author!=null&&this.state.type!=null&&
        //     this.state.inventory!=null&&this.state.price!=null&&this.state.description!=null&&this.image!=null){
        addBook(this.state.name, this.state.isbn, this.state.type, this.state.author, this.state.price, this.state.description,
            this.state.inventory, this.state.image, callback);
        // }
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
            // console.log(e.target.result);// 打印图片的base64
            // if (e && e.target && e.target.result) {
            //     option.onSuccess();
            // }
        };
    }

    beforeUpload(file) {
        const isJpgOrPng = file.type === "image/jpeg" || file.type === "image/png";
        if (!isJpgOrPng) {
            message.error("只能上传JPG或PNG文件!");
            return false;
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
            message.error("图片大小需小于2MB!");
            return false;
        }
        return isJpgOrPng && isLt2M;
    }

    render() {
        // console.log(this.image!=null);
        if (this.state.submit) {
            return (
                <Result
                    status="success"
                    title="添加成功"
                    // subTitle="欢迎来到电子书店"
                    extra={[
                        <Link to={{
                            pathname: '/homeAdmin',
                        }}
                        >
                            <Button type="primary" key="console">
                                回到主页
                            </Button>
                        </Link>
                    ]}
                />
            )
        }
        return (
            <div>
                <div>
                    <Input onBlur={this.handleIsbn}
                           prefix={<p>isbn:</p>}
                    />
                    <Input onBlur={this.handleName}
                           prefix={<p>书名:</p>}
                    />
                    <Input onBlur={this.handleAuthor}
                           prefix={<p>作者:</p>}
                    />
                    <Input onBlur={this.handleType}
                           prefix={<p>分类:</p>}
                    />
                    <Input onBlur={this.handlePrice}
                           prefix={<p>价格:</p>}
                    />
                    <Input onBlur={this.handleInventory}
                           prefix={<p>库存:</p>}
                    />
                    <TextArea onBlur={this.handleDescription}
                              prefix={<p>简介：</p>}
                              rows={10}/>
                </div>
                <div>
                    <Upload {...{
                        customRequest: this.customRequest,
                        showUploadList: false, // 不展示文件列表
                        beforeUpload: this.beforeUpload
                    }}>
                        <Button>
                            <UploadOutlined/> Click to Upload
                        </Button>
                    </Upload>
                </div>
                <Button onClick={this.handleSubmit.bind(this)}>
                    提交
                </Button>
            </div>


        )
    }
}